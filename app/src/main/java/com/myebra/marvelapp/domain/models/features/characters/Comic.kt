package com.myebra.marvelapp.domain.models.features.characters

data class Comic(
    val comicId: Long,
    val title: String,
    val thumbnail: String,
    val onSaleDate: String
)
