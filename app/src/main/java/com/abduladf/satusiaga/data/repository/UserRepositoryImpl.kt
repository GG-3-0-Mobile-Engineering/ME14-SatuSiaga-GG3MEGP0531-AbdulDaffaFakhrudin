package com.abduladf.satusiaga.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.abduladf.satusiaga.domain.UserPreferences
import com.abduladf.satusiaga.domain.repository.UserRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : UserRepository {

    companion object {
        private val UI_MODE_KEY = booleanPreferencesKey("ui_mode")
    }

    override suspend fun getUserPreferences(): UserPreferences {
        val preferences = dataStore.data.first()
        val isDarkModeEnabled = preferences[UI_MODE_KEY] ?: false
        return UserPreferences(isDarkModeEnabled)
    }

    override suspend fun saveUserPreferences(userPreferences: UserPreferences) {
        dataStore.edit { preferences ->
            preferences[UI_MODE_KEY] = userPreferences.isDarkModeEnabled
        }
    }
}