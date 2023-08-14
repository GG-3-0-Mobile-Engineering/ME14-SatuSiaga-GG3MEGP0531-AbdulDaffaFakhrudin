package com.abduladf.satusiaga.feature.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abduladf.satusiaga.domain.model.DisasterItem
import com.abduladf.satusiaga.domain.usecase.MainUseCase
import com.abduladf.satusiaga.utils.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    private val _searchItems = MutableStateFlow<List<String>>(emptyList())
    val searchItems: StateFlow<List<String>> = _searchItems

    private val _disasterItems = MutableStateFlow<List<DisasterItem>>(emptyList())
    val disasterItems: StateFlow<List<DisasterItem>> = _disasterItems

    private var _stateLoading = MutableStateFlow(false)
    val stateLoading = _stateLoading

    fun updateSearchItems(newItems: List<String>) {
        _searchItems.value = newItems
    }

    fun updateDisasterItems(newItems: List<DisasterItem>) {
        _disasterItems.value = newItems
    }

    fun clearDisasterItems() {
        _disasterItems.value = emptyList()
    }

    //test
    fun filterDisasterItems(area: String?, disasterType: String?) {
        viewModelScope.launch {
            mainUseCase.getDisasterList(604800, area, disasterType).collectLatest {
                when (it.status) {
                    ApiStatus.SUCCESS -> {
                        _stateLoading.update { false }
                        val datas = it.data.orEmpty()
                        Log.d("MainViewModel", "filterDisasterItems: $datas")
                        _disasterItems.update {
                            datas.map { data ->
                                DisasterItem(
                                    data.imageUrl,
                                    data.title,
                                    data.subtitle,
                                    data.coordinates,
                                    data.date
                                )
                            }
                        }
                    }
                    ApiStatus.ERROR -> {
                        _stateLoading.update { false }
                    }
                    ApiStatus.LOADING -> {
                        _stateLoading.update { true }
                    }
                }
            }
            Log.d("MainViewModel", "number of disaster items: ${_disasterItems.value.size}")
        }
    }

}