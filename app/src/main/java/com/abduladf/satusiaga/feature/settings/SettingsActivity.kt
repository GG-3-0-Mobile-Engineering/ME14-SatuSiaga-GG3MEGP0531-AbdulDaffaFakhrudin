package com.abduladf.satusiaga.feature.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.abduladf.satusiaga.R
import com.abduladf.satusiaga.databinding.ActivitySettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Settings"

        val binding: ActivitySettingsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_settings)

        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

        // Observe the isDarkModeEnabled LiveData to update the switch state
        viewModel.isDarkModeEnabled.observe(this) { isDarkModeEnabled ->
            binding.switchDarkMode.isChecked = isDarkModeEnabled
            setDarkMode(isDarkModeEnabled)
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveUserPreferences(isChecked)
        }
    }

    private fun setDarkMode(isNightMode: Boolean) {
        // Implement the logic to set the dark mode here
        // For example, you can use AppCompatDelegate.setDefaultNightMode() to set the night mode.
        val mode = if (isNightMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}