package com.app.pmc.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserLocalDataSource {

    private val dataStore: DataStore<Preferences> = context.dataStore

    private object Keys {
        val USER_ID = stringPreferencesKey("user_id")
        val TOKEN = stringPreferencesKey("token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val USER_PASSWORD = stringPreferencesKey("user_password")
    }

    override suspend fun saveUserId(userId: String) {
        dataStore.edit { it[Keys.USER_ID] = userId }
    }

    override suspend fun getUserId(): String? {
        return dataStore.data.first()[Keys.USER_ID]
    }

    override suspend fun deleteUserId() {
        dataStore.edit { it.remove(Keys.USER_ID) }
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { it[Keys.TOKEN] = token }
    }

    override fun getToken(): String? {
        return runBlocking(Dispatchers.IO) {
            dataStore.data.first()[Keys.TOKEN]
        }
    }

    override suspend fun deleteToken() {
        dataStore.edit { it.remove(Keys.TOKEN) }
    }

    override suspend fun saveRefreshToken(refreshToken: String) {
        dataStore.edit { it[Keys.REFRESH_TOKEN] = refreshToken }
    }

    override suspend fun getRefreshToken(): String? {
        return dataStore.data.first()[Keys.REFRESH_TOKEN]
    }

    override suspend fun deleteRefreshToken() {
        dataStore.edit { it.remove(Keys.REFRESH_TOKEN) }
    }

    override suspend fun getPassword(): String? {
       return dataStore.data.first()[Keys.USER_PASSWORD]
    }

    override suspend fun setPassword(password: String) {
        dataStore.edit { it[Keys.USER_PASSWORD] = password }
    }
}