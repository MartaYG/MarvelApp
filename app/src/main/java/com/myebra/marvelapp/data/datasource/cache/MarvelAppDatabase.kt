package com.myebra.marvelapp.data.datasource.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myebra.marvelapp.data.datasource.features.character.cache.dao.CharactersDao
import com.myebra.marvelapp.data.datasource.features.character.cache.model.CacheCharacter
import com.myebra.marvelapp.data.datasource.features.character.cache.model.CacheComicsCharacter

@Database(entities = [
    CacheCharacter::class,
    CacheComicsCharacter::class],
    version = 1, exportSchema = true)
abstract class MarvelAppDatabase : RoomDatabase(){

    abstract fun charactersDao(): CharactersDao
}