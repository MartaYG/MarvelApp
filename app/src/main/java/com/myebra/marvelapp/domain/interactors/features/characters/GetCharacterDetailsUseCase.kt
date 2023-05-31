package com.myebra.marvelapp.domain.interactors.features.characters

import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.domain.repository.features.characters.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetCharacterDetailsUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
){
    open operator fun invoke(characterId: Long) : Flow<Character> =
        charactersRepository.getCharacterDetails(characterId)
}