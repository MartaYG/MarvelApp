package com.myebra.marvelapp.data.datasource.features.character.cache.mapper

import com.myebra.marvelapp.data.datasource.features.character.cache.model.CacheCharacter
import com.myebra.marvelapp.data.datasource.features.character.cache.model.CacheComicsCharacter
import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.domain.models.features.characters.Comic

fun List<CacheCharacter>.allCharactersCacheMapper(): List<Character> =
    this.map {characters ->
        Character(
            characterId = characters.characterId,
            name = characters.name,
            thumbnail = characters.thumbnail,
            comicCount = characters.comicCount,
            description = characters.description
        )
    }

fun CacheCharacter.characterCacheMapper(): Character =
    this.let { character ->
        Character(
            characterId = character.characterId,
            name = character.name,
            thumbnail = character.thumbnail,
            comicCount = character.comicCount,
            description = character.description
        )
    }

fun characterInsertCacheMapper(character: Character) : CacheCharacter =
    CacheCharacter(
        characterId = character.characterId,
        name = character.name,
        thumbnail = character.thumbnail,
        comicCount = character.comicCount,
        description = character.description
    )

fun List<CacheComicsCharacter>.allComicsCharacterCacheMapper(): List<Comic> =
    this.map {comics ->
        Comic(
            comicId = comics.comicId,
            title = comics.title,
            thumbnail = comics.thumbnail,
            onSaleDate = comics.onSaleDate
        )
    }

fun comicsCharacterInsertCacheMapper(comic: Comic,characerId: Long) : CacheComicsCharacter =
    CacheComicsCharacter(
        comicId = comic.comicId,
        title = comic.title,
        thumbnail = comic.thumbnail,
        onSaleDate = comic.onSaleDate,
        characterId = characerId
    )