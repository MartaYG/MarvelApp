package com.myebra.marvelapp.data.repository

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

    override fun getAllCharacters(page : Int, limit : Int): Flow<List<Character>> = channelFlow {
        charactersFactory.remoteCharacterDataStore.getAllCharacters(page=page,limit=limit).collectLatest { remoteCharacters ->
            send(remoteCharacters)
        }
    }

    override fun getCharacterDetails(characterId: Long): Flow<Character> = channelFlow {
        charactersFactory.remoteCharacterDataStore.getCharacterDetails(characterId).collectLatest { remoteCharacter ->
            send(remoteCharacter)
        }
    }

}