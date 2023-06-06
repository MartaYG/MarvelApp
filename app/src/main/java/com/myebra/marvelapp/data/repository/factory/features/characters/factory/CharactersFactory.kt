package com.myebra.marvelapp.data.repository.factory.features.characters.factory

import com.myebra.marvelapp.data.datastore.features.characters.CharacterDataStore
import javax.inject.Inject
import javax.inject.Named

open class CharactersFactory @Inject constructor(
    @Named("remote_characters") val remoteCharacterDataStore: CharacterDataStore,
    @Named("cache_characters") val cacheCharacterDataStore: CharacterDataStore

){
}