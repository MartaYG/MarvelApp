package com.myebra.marvelapp.data.datasource.features.character.remote.models

import com.google.gson.annotations.SerializedName

data class RemoteCharacterResult(
    @SerializedName("results") val charactersResults: List<RemoteSingleCharacter>,
    @SerializedName("offset") val page : Int,
    @SerializedName("limit") val limit : Int
)
