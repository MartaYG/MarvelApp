package com.myebra.marvelapp.ui.features.characterdetails.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.myebra.marvelapp.databinding.ActivityCharacterDetailsBinding
import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.ui.common.ResourceState
import com.myebra.marvelapp.ui.features.characterdetails.viewmodels.CharacterDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class CharacterDetailsActivity : AppCompatActivity() {

    private val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var binding:ActivityCharacterDetailsBinding
    private var loadedCharacter : Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val resultIdCharacter: Long = intent.extras?.getLong("ID_CHARACTER") ?: -1L
        viewModel.fetchCharacterDetails(resultIdCharacter)

        lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                this@CharacterDetailsActivity.viewModel.characterState.collectLatest { character->
                    withContext(Dispatchers.Main){
                        when(character){
                            is ResourceState.Error ->
                                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()

                            is ResourceState.Success -> {
                                loadedCharacter = character.data as Character
                                withContext(Dispatchers.Main){
                                    binding.ivCharacterDetailsThumbnail.load(loadedCharacter?.thumbnail)
                                    binding.tvCharacterDetailsName.text = loadedCharacter?.name
                                    binding.tvCharacterDetailsDescription.text = loadedCharacter?.description
                                }
                            }
                            else -> {}
                        }                    }
                }
            }
        }
    }
}