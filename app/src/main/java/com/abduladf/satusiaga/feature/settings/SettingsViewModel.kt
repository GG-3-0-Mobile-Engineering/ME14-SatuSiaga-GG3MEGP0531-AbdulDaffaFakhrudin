package com.abduladf.satusiaga.feature.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abduladf.satusiaga.domain.UserPreferences
import com.abduladf.satusiaga.domain.usecase.UserPrefUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val userPrefUseCase: UserPrefUseCase) : ViewModel() {

    private val _isDarkModeEnabled = MutableLiveData<Boolean>()
    val isDarkModeEnabled: LiveData<Boolean>
        get() = _isDarkModeEnabled

    init {
        // Initialize the value of _isDarkModeEnabled using a coroutine scope
        viewModelScope.launch {
            _isDarkModeEnabled.value = userPrefUseCase.getUserPreferences().isDarkModeEnabled
        }
    }

    fun saveUserPreferences(isDarkModeEnabled: Boolean) {
        viewModelScope.launch {
            userPrefUseCase.saveUserPreferences(UserPreferences(isDarkModeEnabled))
            _isDarkModeEnabled.value = isDarkModeEnabled
        }
    }
}