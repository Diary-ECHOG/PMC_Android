package com.app.pmc.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideBaseUrl(): String = "https://youtube.googleapis.com/youtube/v3/"

    private val contentType = "application/json".toMediaType()
    val json = Json {
        ignoreUnknownKeys = true  // JSON에서 알 수 없는 필드를 무시
        isLenient = true          // 유연한 JSON 파싱 허용
        encodeDefaults = true     // 기본값을 포함해서 직렬화
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()

    @Provides
    @Singleton
    fun provideDiaryService(): DiaryService {
        return DiaryService()
    }
}