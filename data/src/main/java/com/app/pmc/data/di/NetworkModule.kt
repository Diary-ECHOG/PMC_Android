package com.app.pmc.data.di

import com.app.pmc.data.BuildConfig
import com.app.pmc.data.core.OAuthHeaderInterceptor
import com.app.pmc.data.core.OAuthenticator
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}

@Module
@InstallIn(SingletonComponent::class)
abstract class SafeApiCallModule {

    @Binds
    abstract fun bindSafeApiCall(
        safeApiCallImpl: SafeApiCallImpl
    ): SafeApiCall
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS) // 연결 타임아웃 (기본 10초 → 30초로 증가)
            .readTimeout(30, TimeUnit.SECONDS)    // 읽기 타임아웃
            .writeTimeout(30, TimeUnit.SECONDS)
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
            .build()
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OAuth

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    @OAuth
    fun provideOAuthRetrofit(
        okhttpClientBuilder: OkHttpClient.Builder,
        json: Json,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                okhttpClientBuilder
                    .build()
            )
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}