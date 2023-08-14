package com.abduladf.satusiaga

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import com.abduladf.satusiaga.data.repository.UserRepositoryImpl
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var uiPrefs: UserRepositoryImpl

    private val applicationScope = CoroutineScope(SupervisorJob() + IO)

    override fun onCreate() {
        super.onCreate()

        val mainHandler = Handler(Looper.getMainLooper())

        applicationScope.launch {
            val uiMode = uiPrefs.getUserPreferences().isDarkModeEnabled
            val mode = if (uiMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            mainHandler.post { AppCompatDelegate.setDefaultNightMode(mode) }
        }
    }


    override fun onTerminate() {
        super.onTerminate()
        applicationScope.cancel()
    }
}