package com.myebra.marvelapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.domain.repository.features.characters.CharactersRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

open class CharacterPaging @Inject constructor(
    private val repositoryCharacter: CharactersRepository
): PagingSource<Int,Character>(){

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> =
        try {
            val page = params.key ?: 0
            val limit = 20
            val response = repositoryCharacter.getAllCharacters(
                page=page,
                limit=limit
            ).first()

            LoadResult.Page(
                data = response,
                prevKey = if(page == 0) null else page.minus(1),
                nextKey = if(response.isEmpty()) null else page.plus(1)
            )
        }catch (exception:Exception){
            LoadResult.Error(exception)
        }
}