package com.myebra.marvelapp.domain.repository.features.characters

import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.domain.models.features.characters.Comic
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getAllCharacters(page : Int,limit : Int) : Flow<List<Character>>
    fun getCharacterDetails(characterId: Long) : Flow<Character>
    fun getComicsCharacter(characterId: Long) : Flow<List<Comic>>


}