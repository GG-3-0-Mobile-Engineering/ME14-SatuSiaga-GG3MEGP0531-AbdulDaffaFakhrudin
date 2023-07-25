package com.abduladf.satusiaga

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.abduladf.satusiaga.utils.UIPrefs
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val uiPrefs = UIPrefs(applicationContext)

        GlobalScope.launch(IO) {
            val uiMode = uiPrefs.getUIMode.first()
            val mode = if (uiMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }
}