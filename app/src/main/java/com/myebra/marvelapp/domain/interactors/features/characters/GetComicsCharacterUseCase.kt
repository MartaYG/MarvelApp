package com.myebra.marvelapp.domain.interactors.features.characters

import com.myebra.marvelapp.domain.models.features.characters.Comic
import com.myebra.marvelapp.domain.repository.features.characters.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetComicsCharacterUseCase @Inject constructor(
    private val comicsCharacterRepository: CharactersRepository
) {
    open operator fun invoke(characterId : Long) : Flow<List<Comic>> =
        comicsCharacterRepository.getComicsCharacter(characterId)
}


