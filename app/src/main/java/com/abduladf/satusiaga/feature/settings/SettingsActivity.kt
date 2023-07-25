package com.abduladf.satusiaga.feature.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.abduladf.satusiaga.R
import com.abduladf.satusiaga.databinding.ActivitySettingsBinding
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.abduladf.satusiaga.feature.settings.SettingsViewModel
import kotlinx.coroutines.launch


class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Settings"

        val binding: ActivitySettingsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_settings)

        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        viewModel.initUIPrefs(applicationContext)

        // Collect the UIMode flow to observe changes
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUIMode().collect { isNightMode ->
                    binding.switchDarkMode.isChecked = isNightMode
                }
            }
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveUIMode(isChecked)
            setDarkMode(isChecked)
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