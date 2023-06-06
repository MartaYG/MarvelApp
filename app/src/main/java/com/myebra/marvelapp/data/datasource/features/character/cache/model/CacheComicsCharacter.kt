package com.myebra.marvelapp.data.datasource.features.character.cache.model

import androidx.room.*

@Entity(tableName = "comics_character")
data class CacheComicsCharacter(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "comic_character_id")
    val comicId: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "onSaleDate")
    val onSaleDate: String,
    @ColumnInfo(name = "character_id")
    val characterId: Long?,
)