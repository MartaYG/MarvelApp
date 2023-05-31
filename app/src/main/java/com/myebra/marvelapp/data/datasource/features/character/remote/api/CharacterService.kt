package com.myebra.marvelapp.data.datasource.features.character.remote.api

import com.myebra.marvelapp.BuildConfig
import com.myebra.marvelapp.data.datasource.features.character.remote.models.RemoteAllCharacters
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("characters")
    suspend fun getAllCharacters(
        @Query("offset") page : Int,
        @Query("limit") limit : Int,
        @Query("ts") ts : Int = 1,
        @Query("apikey") apikey : String = BuildConfig.API_KEY,
        @Query("hash") hash : String = BuildConfig.HASH
    ): RemoteAllCharacters

    @GET("characters/{characterId}")
    suspend fun getCharacterDetails(
        @Path("characterId") characterId:Int,
        @Query("ts") ts : Int = 1,
        @Query("apikey") apikey : String = BuildConfig.API_KEY,
        @Query("hash") hash : String = BuildConfig.HASH,
    ): RemoteAllCharacters
}