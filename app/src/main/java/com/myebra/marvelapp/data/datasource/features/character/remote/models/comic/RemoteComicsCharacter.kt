package com.myebra.marvelapp.data.datasource.features.character.remote.models.comic

import com.google.gson.annotations.SerializedName

data class RemoteComicsCharacter(
    @SerializedName("data") val ComicCharacterData: RemoteComicsCharacterResult
)



