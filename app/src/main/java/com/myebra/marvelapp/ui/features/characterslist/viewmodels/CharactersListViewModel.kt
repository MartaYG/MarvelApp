package com.myebra.marvelapp.ui.features.characterslist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.myebra.marvelapp.domain.interactors.features.characters.CharacterPagingUseCase
import com.myebra.marvelapp.domain.interactors.features.characters.GetAllCharactersUseCase
import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.ui.common.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val characterPager: CharacterPagingUseCase
):ViewModel() {

    private val _loadingState by lazy { MutableStateFlow<ResourceState<*>>(ResourceState.Loading(true)) }
    val loadingState: StateFlow<ResourceState<*>> get() = _loadingState

    fun charactersPaging(): Flow<PagingData<Character>> =
        characterPager()
            .catch { error ->
                _loadingState.update { ResourceState.Error(error) }
            }
            .map { pagingData ->
                delay(1000)
                _loadingState.update { ResourceState.Success("") }
                pagingData
            }
}
    
