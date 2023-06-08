package com.myebra.marvelapp.data.repository

import com.myebra.marvelapp.data.repository.factory.features.characters.factory.CharactersFactory
import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.domain.models.features.characters.Comic
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

internal class CharactersRepositoryImplTest{

    @RelaxedMockK
    private lateinit var repositoryImpl: CharactersRepositoryImpl
    private val charactersFactory =  mockk <CharactersFactory>()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        repositoryImpl = CharactersRepositoryImpl(charactersFactory)
    }

    @Test
    fun `getAllCharacters should fetch all characters from remote and cache them when not found in cache`() = runBlocking {

        //Given
        val emptyCharactersList =  emptyList<Character>()
        val allCharacters = listOf(
            Character(1L,"Marta","www.img.es",23,"she is a programmer")
        )

        coEvery { charactersFactory.cacheCharacterDataStore.getAllCharacters(0,20) } returns flowOf(emptyCharactersList)
        coEvery { charactersFactory.remoteCharacterDataStore.getAllCharacters(0,20) } returns flowOf(allCharacters)
        coEvery { charactersFactory.cacheCharacterDataStore.insertAllCharacters(*allCharacters.toTypedArray()) } just Runs

        //When
        val result = repositoryImpl.getAllCharacters(0,20).toList()

        //Then
        assertEquals(allCharacters, result.last())
    }

    @Test
    fun `getAllCharacters shall return a characters list of the cache data stores`() = runBlocking {

        //Given
        val emptyCharactersList =  emptyList<Character>()
        val allCharacters = listOf(
            Character(1L,"Marta","www.img.es",23,"she is a programmer")
        )
        coEvery { charactersFactory.cacheCharacterDataStore.getAllCharacters(0,20) } returns flowOf(allCharacters)
        coEvery { charactersFactory.remoteCharacterDataStore.getAllCharacters(0,20) } returns flowOf(emptyCharactersList)

        //When
        val result = repositoryImpl.getAllCharacters(0,20).toList()

        //Then
        assertEquals(allCharacters, result.last())
    }

    @Test
    fun `getCharacterDetails should fetch character details from remote and cache them when not found in cache`() = runBlocking {

        // Given
        val characterId = 123L
        val emptyCharacter = Character(0L,"","",0,"")
        val characterItem = Character(1L,"Marta","www.img.es",23,"she is a programmer")

        coEvery { charactersFactory.cacheCharacterDataStore.getCharacterDetails(characterId) } returns flowOf(emptyCharacter)
        coEvery { charactersFactory.remoteCharacterDataStore.getCharacterDetails(characterId) } returns flowOf(characterItem)
        coEvery { charactersFactory.cacheCharacterDataStore.insertAllCharacters(characterItem) } just Runs

        //When
        val result =  repositoryImpl.getCharacterDetails(characterId).singleOrNull() ?: emptyCharacter

        //Then
        assertEquals(characterItem, result)
    }

    @Test
    fun `getCharacterDetails shall return a character of the cache data stores`() = runBlocking {

        //Given
        val characterId = 123L
        val emptyCharacter = Character(0L,"","",0,"")
        val characterItem = Character(1L,"Marta","www.img.es",23,"she is a programmer")

        coEvery { charactersFactory.cacheCharacterDataStore.getCharacterDetails(characterId) } returns flowOf(characterItem)
        coEvery { charactersFactory.remoteCharacterDataStore.getCharacterDetails(characterId) } returns flowOf(emptyCharacter)

        //When
        val result =  repositoryImpl.getCharacterDetails(characterId).singleOrNull() ?: emptyCharacter

        //Then
        assertEquals(characterItem, result)
    }

    @Test
    fun `getAllComicsCharacter must return an empty comics list when the remote data stores and cache are empty`() = runBlocking {

        // Given
        val characterId = 123L
        val emptyComics = emptyList<Comic>()

        coEvery { charactersFactory.cacheCharacterDataStore.getComicsCharacter(characterId) } returns flowOf(emptyComics)
        coEvery { charactersFactory.remoteCharacterDataStore.getComicsCharacter(characterId) } returns flowOf(emptyComics)

        // When
        val result = repositoryImpl.getComicsCharacter(characterId).toList()

        // Then
        assertEquals(emptyComics, result.last())
    }

    @Test
    fun `getAllComicsCharacter should fetch all comics from remote and cache them when not found in cache`() = runBlocking {

        //Given
        val characterId = 123L
        val emptyComicsList =  emptyList<Comic>()
        val allComics = listOf(
            Comic(1L,"Marta","www.img.es","11/04/1996")
        )

        coEvery { charactersFactory.cacheCharacterDataStore.getComicsCharacter(characterId) } returns flowOf(emptyComicsList)
        coEvery { charactersFactory.remoteCharacterDataStore.getComicsCharacter(characterId) } returns flowOf(allComics)
        coEvery { charactersFactory.cacheCharacterDataStore.insertAllComicsCharacter(*allComics.toTypedArray(),characterId=characterId) } just Runs

        //When
        val result = repositoryImpl.getComicsCharacter(characterId).toList()

        //Then
        assertEquals(allComics, result.last())
    }

    @Test
    fun `getAllComicsCharacter shall return a comics list of the cache data stores`() = runBlocking {

        //Given
        val characterId = 123L
        val emptyComicsList =  emptyList<Comic>()
        val allComics = listOf(
            Comic(1L,"Marta","www.img.es","11/04/1996")
        )

        coEvery { charactersFactory.cacheCharacterDataStore.getComicsCharacter(characterId) } returns flowOf(allComics)
        coEvery { charactersFactory.remoteCharacterDataStore.getComicsCharacter(characterId) } returns flowOf(emptyComicsList)

        //When
        val result = repositoryImpl.getComicsCharacter(characterId).toList()

        //Then
        assertEquals(allComics, result.last())
    }
}