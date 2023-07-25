package com.abduladf.satusiaga.domain.usecase

import com.abduladf.satusiaga.domain.UserPreferences
import com.abduladf.satusiaga.domain.repository.UserRepository
import javax.inject.Inject

class UserPrefUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend fun getUserPreferences(): UserPreferences {
        return userRepository.getUserPreferences()
    }
    suspend fun saveUserPreferences(userPreferences: UserPreferences){
        userRepository.saveUserPreferences(userPreferences)
    }
}