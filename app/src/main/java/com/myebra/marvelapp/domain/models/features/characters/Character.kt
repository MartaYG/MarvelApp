package com.myebra.marvelapp.domain.models.features.characters

data class Character (
    val characterId : Long,
    val name : String,
    val thumbnail: String,
    val comicCount: Int,
    val description: String
)