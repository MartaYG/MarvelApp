package com.myebra.marvelapp.data.datasource.features.character.remote.models

import com.google.gson.annotations.SerializedName

data class RemoteCharacters(
    @SerializedName("data") val dataCharacters: RemoteCharacterResult
)

