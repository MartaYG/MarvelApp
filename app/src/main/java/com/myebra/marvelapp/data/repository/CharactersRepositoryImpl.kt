package com.myebra.marvelapp.data.repository

import android.util.Log
import com.myebra.marvelapp.data.repository.factory.features.characters.factory.CharactersFactory
import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.domain.repository.features.characters.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersFactory: CharactersFactory
) : CharactersRepository{

    override fun getAllCharacters(): Flow<List<Character>> = channelFlow {
        charactersFactory.remoteCharacterDataStore.getAllCharacters().collectLatest { remoteCharacters ->
            try{
                send(remoteCharacters)

            }catch (exception:Exception){
                Log.e("Error", exception.message ?: exception.toString())
                send(emptyList())
            }
        }
    }


}