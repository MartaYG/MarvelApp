package com.myebra.marvelapp.ui.features.characterslist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myebra.marvelapp.domain.interactors.features.characters.GetAllCharactersUseCase
import com.myebra.marvelapp.domain.models.features.characters.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
):ViewModel(){

    private val _allCharacters : MutableStateFlow<List<Character>> by lazy { MutableStateFlow(listOf())}
    val allCharacters : StateFlow<List<Character>> get() = _allCharacters

    init{
        viewModelScope.launch(Dispatchers.IO) {
            getAllCharactersUseCase().collectLatest { characters ->
                _allCharacters.update {
                    characters
                }
            }
        }
    }
}