package com.myebra.marvelapp.data.datasource.features.character.remote.models

import com.google.gson.annotations.SerializedName

data class RemoteCharacterThumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String,
)
