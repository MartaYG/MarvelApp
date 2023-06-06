package com.myebra.marvelapp.data.repository

import com.myebra.marvelapp.data.repository.factory.features.characters.factory.CharactersFactory
import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.domain.models.features.characters.Comic
import com.myebra.marvelapp.domain.repository.features.characters.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersFactory: CharactersFactory
) : CharactersRepository{

    override fun getAllCharacters(page : Int, limit : Int): Flow<List<Character>> = channelFlow {
        charactersFactory.cacheCharacterDataStore.getAllCharacters(page=page,limit=limit).collectLatest {cacheCharacters ->
            if(cacheCharacters.isEmpty()){
                charactersFactory.remoteCharacterDataStore.getAllCharacters(page=page,limit=limit).collectLatest { remoteCharacters ->
                    charactersFactory.cacheCharacterDataStore.insertAllCharacters(*remoteCharacters.toTypedArray())
                    send(remoteCharacters)
                }
            }else{
                send(cacheCharacters)
            }
        }
    }

    override fun getCharacterDetails(characterId: Long): Flow<Character> = channelFlow {
        charactersFactory.cacheCharacterDataStore.getCharacterDetails(characterId).collectLatest { cacheCharacter ->
            if(cacheCharacter.characterId == 0L){
                charactersFactory.remoteCharacterDataStore.getCharacterDetails(characterId).collectLatest { remoteCharacter ->
                    charactersFactory.cacheCharacterDataStore.insertAllCharacters(remoteCharacter)
                    send(remoteCharacter)
                }
            }else{
                send(cacheCharacter)
            }

        }
    }

    override fun getComicsCharacter(characterId: Long): Flow<List<Comic>> = channelFlow {
        charactersFactory.cacheCharacterDataStore.getComicsCharacter(characterId).collectLatest {cacheComicsCharacter ->
            if(cacheComicsCharacter.isEmpty()){
                try {
                    charactersFactory.remoteCharacterDataStore.getComicsCharacter(characterId)
                        .collectLatest { remoteComicsCharacter ->
                            charactersFactory.cacheCharacterDataStore.insertAllComicsCharacter(
                                *remoteComicsCharacter.toTypedArray(),
                                characterId = characterId
                            )
                            send(remoteComicsCharacter.toList())
                        }
                }catch (e: Exception){
                    send(emptyList())
                }
            }else{
                send(cacheComicsCharacter.toList())
            }
        }
    }
}