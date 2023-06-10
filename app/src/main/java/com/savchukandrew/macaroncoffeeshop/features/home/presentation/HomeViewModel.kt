package com.savchukandrew.macaroncoffeeshop.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.SectionEntities
import com.savchukandrew.macaroncoffeeshop.features.home.domain.usecases.GetSectionProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSectionProductsUseCase: GetSectionProductsUseCase
) : ViewModel() {

    private var _sectionList: MutableLiveData<List<SectionEntities>?> = MutableLiveData()
    val sectionList: LiveData<List<SectionEntities>?> = _sectionList

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _isLoading.value = true
        viewModelScope.launch {
            _sectionList.value = getSectionProductsUseCase()
            _isLoading.value = false
        }
    }
}
