package com.myebra.marvelapp.domain.interactors.features.characters

import com.myebra.marvelapp.domain.repository.features.characters.CharactersRepository
import javax.inject.Inject

open class GetAllCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
){
/*
    open operator fun invoke() : Flow<List<Character>> =
        charactersRepository.getAllCharacters()*/
}