package com.abduladf.satusiaga.feature.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.abduladf.satusiaga.R
import com.abduladf.satusiaga.databinding.ActivityMainBinding
import com.abduladf.satusiaga.feature.settings.SettingsActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.search.SearchView.TransitionState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //set search bar menu
        binding.searchBar.inflateMenu(R.menu.searchbar_menu)
        //searchBar item click
        binding.searchBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_settings -> {
                    val settingsIntent = Intent(this, SettingsActivity::class.java)
                    startActivity(settingsIntent)
                    true
                }

                else -> false
            }
        }

        //handle view transition to hide views when search view is expanded
        binding.searchView.addTransitionListener { searchView, previousState, newState ->
            if (newState === TransitionState.SHOWING) {
                binding.chipHorizontalScrollView.visibility = android.view.View.INVISIBLE
                binding.standardBottomSheet.visibility = android.view.View.INVISIBLE
            }
            if (newState === TransitionState.HIDDEN) {
                binding.chipHorizontalScrollView.visibility = android.view.View.VISIBLE
                binding.standardBottomSheet.visibility = android.view.View.VISIBLE
            }
        }

        //handle chip group selection
        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->
            val checkecChipId = binding.chipGroup.checkedChipId
            if (checkecChipId != -1) {
                val chip: com.google.android.material.chip.Chip? = findViewById(checkecChipId)
                val chipText = chip?.text.toString()
                Log.d("MyChipGroup", "Checked chip is $chipText")
            }
        }

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.standardBottomSheet)
//        bottomSheetBehavior.isHideable = false
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

    }

    override fun onBackPressed() {
        if (binding.searchView.isShowing) {
            binding.searchView.hide()
        } else {
            super.onBackPressed()
        }
    }

}
