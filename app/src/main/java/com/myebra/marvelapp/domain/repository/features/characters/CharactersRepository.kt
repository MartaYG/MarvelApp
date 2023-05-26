package com.myebra.marvelapp.domain.repository.features.characters

import com.myebra.marvelapp.domain.models.features.characters.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getAllCharacters() : Flow<List<Character>>
}