package com.myebra.marvelapp.di

import com.myebra.marvelapp.BuildConfig
import com.myebra.marvelapp.data.datasource.features.character.remote.api.CharacterService
import com.myebra.marvelapp.data.datasource.remote.service.RemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(RemoteService.httpClient())
        .build()

    @Singleton
    @Provides
    fun providesCharacterServiceClient(retrofit: Retrofit) : CharacterService =
        retrofit.create(CharacterService::class.java)
}