package com.myebra.marvelapp.data.datasource.features.character.remote.models

import com.google.gson.annotations.SerializedName

data class RemoteCharacterComicCount(
    @SerializedName("available") val comicCount : Int,
)
