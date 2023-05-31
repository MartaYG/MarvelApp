package com.myebra.marvelapp.ui.features.characterdetails.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myebra.marvelapp.domain.interactors.features.characters.GetCharacterDetailsUseCase
import com.myebra.marvelapp.ui.common.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
):ViewModel(){

    private val _characterState by lazy { MutableStateFlow<ResourceState<*>>(ResourceState.Idle) }
    val characterState: StateFlow<ResourceState<*>> get() = _characterState

    fun fetchCharacterDetails(idCharacter: Long){

        viewModelScope.launch(Dispatchers.IO) {
            _characterState.update { ResourceState.Loading("") }
            getCharacterDetailsUseCase(idCharacter)
                .catch {error ->
                    _characterState.update { ResourceState.Error(error) }
                }
                .collectLatest{ characterDetails->
                    _characterState.update { ResourceState.Success(characterDetails) }
                }
        }
    }
}