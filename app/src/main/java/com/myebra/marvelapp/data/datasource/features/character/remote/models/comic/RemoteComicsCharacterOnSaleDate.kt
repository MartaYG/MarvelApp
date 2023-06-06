package com.myebra.marvelapp.data.datasource.features.character.remote.models.comic

import com.google.gson.annotations.SerializedName

data class RemoteComicsCharacterOnSaleDate(
    @SerializedName("type") val type: String,
    @SerializedName("date")val date: String,
)
