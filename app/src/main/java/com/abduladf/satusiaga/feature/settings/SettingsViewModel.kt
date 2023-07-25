package com.abduladf.satusiaga.feature.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abduladf.satusiaga.utils.UIPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private lateinit var uiPrefs: UIPrefs

    fun initUIPrefs(context: Context) {
        uiPrefs = UIPrefs(context)
    }

    fun getUIMode(): Flow<Boolean> = uiPrefs.getUIMode

    fun saveUIMode(isNightMode: Boolean) {
        viewModelScope.launch {
            uiPrefs.saveToThemePref(isNightMode)

        }
    }
}