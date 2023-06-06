package com.myebra.marvelapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.myebra.marvelapp.ui.features.characterdetails.ui.CharacterDetailsActivity
import com.myebra.marvelapp.ui.features.characterslist.ui.CharactersListScreen
import com.myebra.marvelapp.ui.features.characterslist.viewmodels.CharactersListViewModel
import com.myebra.marvelapp.ui.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val charactersListViewModel : CharactersListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            Thread.sleep(1000)
        }

        setContent {
            MarvelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CharactersListScreen(
                        charactersListViewModel = charactersListViewModel,
                        onItemClick = { characterId -> launchCharacterDetails(characterId) }
                    )
                }
            }
        }
    }
    private fun launchCharacterDetails(idCharacter: Long){
        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra("ID_CHARACTER",idCharacter)
        this.startActivity(intent)
    }
}
