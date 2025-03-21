package com.app.pmc.data.di

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
    internal fun provideCategoryService(
        @NetworkModule.Echog retrofit: Retrofit
    ): UserService = retrofit.create(UserService::class.java)
}