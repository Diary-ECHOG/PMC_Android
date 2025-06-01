package com.app.pmc.data.di

import com.app.pmc.data.core.OAuthService
import com.app.pmc.data.diary.DiaryService
import com.app.pmc.data.user.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class ServiceModule {
    @Provides
    internal fun provideUserService(
        @NetworkModule.Echog retrofit: Retrofit
    ): UserService = retrofit.create(UserService::class.java)

    @Provides
    internal fun provideDiaryService(
        @NetworkModule.Echog retrofit: Retrofit
    ): DiaryService = retrofit.create(DiaryService::class.java)

    @Provides
    internal fun provideOAuthService(
        @NetworkModule.OAuth retrofit: Retrofit
    ): OAuthService = retrofit.create(OAuthService::class.java)
}