package com.myebra.marvelapp.data.datasource.features.character.cache.impl

import com.myebra.marvelapp.data.datasource.cache.MarvelAppDatabase
import com.myebra.marvelapp.data.datasource.features.character.cache.mapper.allCharactersCacheMapper
import com.myebra.marvelapp.data.datasource.features.character.cache.mapper.allComicsCharacterCacheMapper
import com.myebra.marvelapp.data.datasource.features.character.cache.mapper.characterCacheMapper
import com.myebra.marvelapp.data.datasource.features.character.cache.mapper.characterInsertCacheMapper
import com.myebra.marvelapp.data.datasource.features.character.cache.mapper.comicsCharacterInsertCacheMapper
import com.myebra.marvelapp.data.datastore.features.characters.CharacterDataStore
import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.domain.models.features.characters.Comic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterCacheDataStoreImpl @Inject constructor(
    private val charactersDatabase: MarvelAppDatabase
): CharacterDataStore {

    override fun getAllCharacters(page : Int, limit : Int): Flow<List<Character>> = flow{
        charactersDatabase.charactersDao().getAllCharacters(page = page, limit = limit).collect{ characters ->
            emit(characters.allCharactersCacheMapper())
        }
    }

    override fun getCharacterDetails(characterId: Long) : Flow<Character> = flow{
        charactersDatabase.charactersDao().getCharacterDetails(characterId=characterId).collect{ character ->
            emit(character.characterCacheMapper())
        }
    }

    override suspend fun insertAllCharacters(vararg characters: Character) {
        charactersDatabase.charactersDao().insertAllCharacters(*characters.map { character ->
            characterInsertCacheMapper(character)
        }.toTypedArray())
    }

    override fun getComicsCharacter(characterId: Long): Flow<List<Comic>> = flow {
        charactersDatabase.charactersDao().getAllComicsCharacter(characterId).collect{ comics ->
            emit(comics.allComicsCharacterCacheMapper())
        }
    }

    override suspend fun insertAllComicsCharacter(vararg comics: Comic, characterId: Long) {
        charactersDatabase.charactersDao().insertAllComicsCharacter(*comics.toList().map { comic ->
            comicsCharacterInsertCacheMapper(comic,characterId)
        }.toTypedArray())
    }
}