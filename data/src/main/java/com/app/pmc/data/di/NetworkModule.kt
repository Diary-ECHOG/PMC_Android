package com.app.pmc.data.di

import com.app.pmc.data.core.OAuthHeaderInterceptor
import com.app.pmc.data.core.OAuthenticator
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
    }

    @Provides
    fun provideOkHttpClient(
        okhttpClientBuilder: OkHttpClient.Builder,
        oAuthenticator: OAuthenticator
    ): OkHttpClient {
        return okhttpClientBuilder
            .authenticator(oAuthenticator)
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
            encodeDefaults = true
            prettyPrint = true
        }
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Echog

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    @Echog
    fun provideRetrofit(
        okhttpClientBuilder: OkHttpClient.Builder,
        json: Json,
        oAuthenticator: OAuthenticator,
        oAuthHeaderInterceptor: OAuthHeaderInterceptor,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                okhttpClientBuilder
                    .authenticator(oAuthenticator)
                    .addInterceptor(oAuthHeaderInterceptor)
                    .build()
            )
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ResultCallAdapter.Factory())
            .build()
    }
}