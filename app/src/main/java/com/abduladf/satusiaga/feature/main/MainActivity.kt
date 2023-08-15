package com.abduladf.satusiaga.feature.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.abduladf.satusiaga.R
import com.abduladf.satusiaga.databinding.ActivityMainBinding
import com.abduladf.satusiaga.feature.main.disasteritem.DisasterItemAdapter
import com.abduladf.satusiaga.feature.main.searchitem.SearchItemAdapter
import com.abduladf.satusiaga.feature.settings.SettingsActivity
import com.abduladf.satusiaga.utils.SearchItemsUtil.searchItemsMap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.chip.Chip
import com.google.android.material.search.SearchView.TransitionState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //set map fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.maps_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        Log.d("MyMap showing", binding.mapsFragment.visibility.toString())

        //init disaster items with latest data
        viewModel.filterDisasterItems(null, null)

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

        //set search view adapter
        val searchItemAdapter = SearchItemAdapter { itemText ->
            binding.searchView.hide()
            binding.searchBar.setText(itemText)
            viewModel.filterDisasterItems(searchItemsMap[itemText], null)
            binding.chipGroup.clearCheck()
        }
        binding.searchRecyclerView.adapter = searchItemAdapter
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this)

        //set bottom sheet recycler view adapter for disaster items
        val disasterItemAdapter = DisasterItemAdapter()
        binding.bottomSheetRecyclerView.adapter = disasterItemAdapter
        binding.bottomSheetRecyclerView.layoutManager = LinearLayoutManager(this)

        //observe state of search items
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchItems.collect { newItems ->
                    searchItemAdapter.updateData(newItems)
                }
            }
        }

        //observe state of disaster items
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.disasterItems.collect { newItems ->
                    disasterItemAdapter.updateData(newItems)
                    if (newItems.isEmpty()) {
                        binding.noDataTextView.visibility = android.view.View.VISIBLE
                    } else {
                        binding.noDataTextView.visibility = android.view.View.GONE
                    }
                }
            }
        }

        //handle search view text change
        val editText = binding.searchView.editText
        editText.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                val filteredItems = searchItemsMap.keys.filter { it.contains(s.toString(), ignoreCase = true) }
                viewModel.updateSearchItems(filteredItems)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //do nothing
            }
        })

        //handle search view submit
        binding.searchView
            .getEditText()
            .setOnEditorActionListener { v, actionId, event ->
                binding.searchBar.setText(binding.searchView.getText())
                binding.searchView.hide()
                false
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
            binding.searchBar.clearText()
            if (checkedId.isEmpty()) {
                viewModel.filterDisasterItems(null, null)
                Log.d("MyChipGroup", "No chip checked")
            } else {
                val chip: Chip? = findViewById(checkedId[0])
                val chipText = chip?.text.toString()
                viewModel.filterDisasterItems(null, chipText.lowercase())
            }
        }

    }

    override fun onBackPressed() {
        if (binding.searchView.isShowing) {
            binding.searchView.hide()
        } else {
            super.onBackPressed()
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        //set map style checking if dark mode is enabled
        val isDarkMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (isDarkMode == Configuration.UI_MODE_NIGHT_YES) {
            mMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style_dark))
        } else {
            mMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style_light))
        }

        //observe state of disaster items
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.disasterItems.collect { newItems ->
                    if (newItems.isNotEmpty()) {
                        //take at most 5 latest disaster items
                        val disasterItems = newItems.take(5)
                        Log.d("MyMap", "values = $disasterItems")
                        mMap?.clear()
                        for (item in disasterItems) {
                            mMap?.addMarker(MarkerOptions().position(LatLng(item.coordinates[1], item.coordinates[0])).title(item.title))
                        }
                        //move camera to the first disaster item
                        val targetLatLng = LatLng(
                            disasterItems[0].coordinates[1],
                            disasterItems[0].coordinates[0]
                        )
                        val zoomLevel = 11.0f // You can adjust the zoom level as needed


                        val cameraUpdate =
                            CameraUpdateFactory.newLatLngZoom(targetLatLng, zoomLevel)
                        mMap!!.animateCamera(cameraUpdate)
                    }
                }
            }
        }
    }
}
