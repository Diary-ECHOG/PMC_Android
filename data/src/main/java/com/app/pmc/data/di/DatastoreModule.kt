package com.app.pmc.data.di

import com.app.pmc.core.repository.UserRepository
import com.app.pmc.data.local.UserLocalDataSource
import com.app.pmc.data.local.UserLocalDataSourceImpl
import com.app.pmc.data.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatastoreModule {
    @Binds
    @Singleton
    abstract fun bindUserDataStore(
        impl: UserLocalDataSourceImpl
    ): UserLocalDataSource
}