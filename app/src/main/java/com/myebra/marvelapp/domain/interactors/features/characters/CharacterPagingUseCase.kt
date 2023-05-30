package com.myebra.marvelapp.domain.interactors.features.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.myebra.marvelapp.data.repository.CharacterPaging
import javax.inject.Inject

open class CharacterPagingUseCase @Inject constructor(
    private val pager: CharacterPaging
){

    open operator fun invoke() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = { pager }
    ).flow
}