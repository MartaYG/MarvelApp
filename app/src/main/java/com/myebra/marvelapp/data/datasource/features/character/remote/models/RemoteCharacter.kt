package com.myebra.marvelapp.data.datasource.features.character.remote.models

import com.google.gson.annotations.SerializedName

data class RemoteCharacter(
    @SerializedName("id") val characterId : Int,
    @SerializedName("name") val name : String,
    @SerializedName("thumbnail") val thumbnail : RemoteCharacterThumbnail,
    @SerializedName("comics") val comics : RemoteCharacterComicCount,
    @SerializedName("description") val description : String,

    )
