package com.myebra.marvelapp.ui.common

sealed class ResourceState<T> {
    object Idle: ResourceState<Void>()
    data class Loading<Boolean>(val isLoading: Boolean): ResourceState<Boolean>()
    data class Error<Throwable>(val throwable: Throwable): ResourceState<Throwable>()
    data class Success<T>(val data: T): ResourceState<T>()
}