package com.myebra.marvelapp.data.datasource.features.character.cache.dao

import androidx.room.*
import com.myebra.marvelapp.data.datasource.features.character.cache.model.CacheCharacter
import com.myebra.marvelapp.data.datasource.features.character.cache.model.CacheComicsCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Transaction
    @Query("SELECT * FROM characters LIMIT :limit OFFSET :limit * :page")
    fun getAllCharacters(page: Int, limit: Int): Flow<List<CacheCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(vararg allCharacter: CacheCharacter)

    @Delete
    suspend fun deleteAllCharacters(vararg allCharacter: CacheCharacter)

    @Transaction
    @Query("SELECT * FROM characters WHERE character_id = :characterId")
    fun getCharacterDetails(characterId: Long): Flow<CacheCharacter>

    @Transaction
    @Query("SELECT * FROM comics_character WHERE character_id = :characterId")
    fun getAllComicsCharacter(characterId: Long): Flow<List<CacheComicsCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllComicsCharacter(vararg allComicsCharacter: CacheComicsCharacter)
}