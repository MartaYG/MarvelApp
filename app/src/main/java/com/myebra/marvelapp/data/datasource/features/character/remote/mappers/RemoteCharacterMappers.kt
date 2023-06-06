package com.myebra.marvelapp.data.datasource.features.character.remote.mappers

import com.myebra.marvelapp.data.datasource.features.character.remote.models.RemoteCharacters
import com.myebra.marvelapp.data.datasource.features.character.remote.models.comic.RemoteComicsCharacter
import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.domain.models.features.characters.Comic
import java.text.SimpleDateFormat
import java.util.*

fun RemoteCharacters.allCharactersMapper(): List<Character> =
    this.dataCharacters.charactersResults.map {remoteCharacter ->
        Character(
           characterId = remoteCharacter.characterId.toLong(),
            name = remoteCharacter.name,
            thumbnail = "${remoteCharacter.thumbnail.path}.${remoteCharacter.thumbnail.extension}",
            comicCount = remoteCharacter.comics.comicCount,
            description = remoteCharacter.description
        )
    }

fun RemoteCharacters.characterMapper(): Character{
    val remoteCharacter = this.dataCharacters.charactersResults[0]
    return Character(
        characterId = remoteCharacter.characterId.toLong(),
        name = remoteCharacter.name,
        thumbnail = "${remoteCharacter.thumbnail.path}.${remoteCharacter.thumbnail.extension}",
        comicCount = remoteCharacter.comics.comicCount,
        description = remoteCharacter.description
    )
}

fun RemoteComicsCharacter.comicsCharacterDetailMapper(): List<Comic> {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        val newDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return this.ComicCharacterData.comicCharacterResults.map { remoteComicCharacter ->
            val onSaleDate = remoteComicCharacter.dates[0].date
            val date = dateFormatter.parse(onSaleDate)
            val formattedDate = date?.let { newDateFormat.format(it) }

            Comic(
                comicId = remoteComicCharacter.comicId.toLong(),
                title = remoteComicCharacter.title,
                thumbnail = "${remoteComicCharacter.thumbnail.path}.${remoteComicCharacter.thumbnail.extension}",
                onSaleDate = formattedDate ?: "?"
            )
        }
}

