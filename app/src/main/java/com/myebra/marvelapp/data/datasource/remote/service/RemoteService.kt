package com.myebra.marvelapp.data.datasource.remote.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

abstract class RemoteService {

    companion object {

        private fun logginInterceptorCreate(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

        fun httpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logginInterceptorCreate())
            .build()
    }
}