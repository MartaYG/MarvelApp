package com.myebra.marvelapp.ui.features.characterdetails.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.myebra.marvelapp.R
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

        val comicView = binding.cvComicsCharacter
        val resultIdCharacter: Long = intent.extras?.getLong("ID_CHARACTER") ?: -1L
        viewModel.fetchCharacterDetails(resultIdCharacter)

        lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                this@CharacterDetailsActivity.viewModel.characterState.collectLatest { character->
                    withContext(Dispatchers.Main){
                        when(character){
                            is ResourceState.Loading ->{
                                val loading = character.load as Int
                                binding.pbCharacter.visibility = loading
                            }
                            is ResourceState.Error -> {
                                binding.pbCharacter.visibility = View.GONE
                                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                            }
                            is ResourceState.Success -> {
                                binding.pbCharacter.visibility = View.GONE
                                loadedCharacter = character.data as Character
                                    binding.ivCharacterDetailsThumbnail.load(loadedCharacter?.thumbnail)
                                    binding.tvCharacterDetailsName.text = loadedCharacter?.name
                                    if (loadedCharacter?.description?.isNotEmpty() == true){
                                        binding.tvCharacterDetailsDescription.text = loadedCharacter?.description
                                    }else{
                                        binding.tvCharacterDetailsDescription.text = getString(R.string.character_details_description_empty)
                                    }
                                 comicView.setContent { ComicListCharacter(characterDetailsViewModel = viewModel, resultIdCharacter) }
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}

