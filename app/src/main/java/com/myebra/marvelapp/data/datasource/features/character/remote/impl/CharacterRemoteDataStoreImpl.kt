package com.myebra.marvelapp.data.datasource.features.character.remote.impl

import com.myebra.marvelapp.data.datasource.features.character.remote.api.CharacterService
import com.myebra.marvelapp.data.datasource.features.character.remote.mappers.allCharactersMapper
import com.myebra.marvelapp.data.datastore.features.characters.CharacterDataStore
import com.myebra.marvelapp.domain.models.features.characters.Character
import kotlinx.coroutines.flow.*


import javax.inject.Inject

class CharacterRemoteDataStoreImpl @Inject constructor(
    private val characterService: CharacterService
):CharacterDataStore{

    override fun getAllCharacters(page : Int, limit : Int): Flow<List<Character>> = flow{
        emit(characterService.getAllCharacters(page = page*limit, limit = limit).allCharactersMapper())
    }
}