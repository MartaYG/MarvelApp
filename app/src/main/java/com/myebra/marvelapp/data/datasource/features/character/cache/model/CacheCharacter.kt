package com.myebra.marvelapp.data.datasource.features.character.cache.model

import androidx.room.*

@Entity(tableName = "characters")
data class CacheCharacter(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "character_id")
    val characterId : Long,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "comic_count")
    val comicCount: Int,
    @ColumnInfo(name = "description")
    val description: String
    )