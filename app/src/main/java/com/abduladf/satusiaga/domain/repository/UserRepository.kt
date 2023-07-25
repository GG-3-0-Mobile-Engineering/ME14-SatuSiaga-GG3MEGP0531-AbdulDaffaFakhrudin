package com.abduladf.satusiaga.domain.repository

import com.abduladf.satusiaga.domain.UserPreferences

interface UserRepository {
    suspend fun getUserPreferences(): UserPreferences
    suspend fun saveUserPreferences(userPreferences: UserPreferences)
}