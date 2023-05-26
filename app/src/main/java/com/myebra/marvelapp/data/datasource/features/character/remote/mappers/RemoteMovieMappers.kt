package com.myebra.marvelapp.data.datasource.features.character.remote.mappers

import com.myebra.marvelapp.data.datasource.features.character.remote.models.RemoteAllCharacters
import com.myebra.marvelapp.domain.models.features.characters.Character

fun RemoteAllCharacters.allCharactersMapper(): List<Character> =
    this.dataCharacters.charactersResults.map {remoteCharacter ->
        Character(
           characterId = remoteCharacter.characterId.toLong(),
            name = remoteCharacter.name,
            thumbnail = "${remoteCharacter.thumbnail.path}.${remoteCharacter.thumbnail.extension}",
            comicCount = remoteCharacter.comics.comicCount,
            description = remoteCharacter.description
        )
    }