package com.myebra.marvelapp.data.datastore.features.characters

import com.myebra.marvelapp.domain.models.features.characters.Character
import kotlinx.coroutines.flow.Flow

interface CharacterDataStore {

    fun getAllCharacters(page : Int,limit : Int): Flow<List<Character>>
}