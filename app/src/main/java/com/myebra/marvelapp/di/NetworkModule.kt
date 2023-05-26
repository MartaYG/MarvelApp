package com.myebra.marvelapp.di

import com.myebra.marvelapp.BuildConfig
import com.myebra.marvelapp.data.datasource.features.character.remote.api.CharacterService
import com.myebra.marvelapp.data.datasource.features.character.remote.impl.CharacterRemoteDataStoreImpl
import com.myebra.marvelapp.data.datasource.remote.service.RemoteService
import com.myebra.marvelapp.data.datastore.features.characters.CharacterDataStore
import com.myebra.marvelapp.data.repository.CharactersRepositoryImpl
import com.myebra.marvelapp.data.repository.factory.features.characters.factory.CharactersFactory
import com.myebra.marvelapp.domain.repository.features.characters.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Named("remote_characters")
    @Provides
    fun providesCharacterRemoteDataStore(characterService: CharacterService) : CharacterDataStore =
        CharacterRemoteDataStoreImpl(characterService)

    @Provides
    fun providesCharactersRepository(charactersFactory: CharactersFactory) : CharactersRepository =
        CharactersRepositoryImpl(charactersFactory)

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