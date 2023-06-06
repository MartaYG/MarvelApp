package com.myebra.marvelapp.data.datasource.features.character.remote.models.comic

import com.google.gson.annotations.SerializedName

data class RemoteComicsCharacterResult(
    @SerializedName("results") val comicCharacterResults: List<RemoteSingleComicCharacter>,
    @SerializedName("offset") val page : Int,
    @SerializedName("limit") val limit : Int
)