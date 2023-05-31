package com.myebra.marvelapp.ui.common

sealed class ResourceState<T> {
    object Idle: ResourceState<Void>()
    data class Loading<T>(val load: T): ResourceState<T>()
    data class Error<Throwable>(val throwable: Throwable): ResourceState<Throwable>()
    data class Success<T>(val data: T): ResourceState<T>()
}