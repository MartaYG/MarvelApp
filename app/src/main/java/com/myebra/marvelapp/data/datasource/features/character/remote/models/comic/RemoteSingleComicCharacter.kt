package com.myebra.marvelapp.data.datasource.features.character.remote.models.comic

import com.google.gson.annotations.SerializedName
import com.myebra.marvelapp.data.datasource.features.character.remote.models.RemoteCharacterThumbnail
import java.util.*

data class RemoteSingleComicCharacter(
    @SerializedName("id") val comicId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: RemoteCharacterThumbnail,
    @SerializedName("dates")val dates: List<RemoteComicsCharacterOnSaleDate>
)
