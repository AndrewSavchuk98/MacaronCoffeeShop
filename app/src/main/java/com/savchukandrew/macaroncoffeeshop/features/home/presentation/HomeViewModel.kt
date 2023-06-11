package com.savchukandrew.macaroncoffeeshop.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.Product
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

    private var _productList: MutableLiveData<List<Product>?> = MutableLiveData()
    val productList: LiveData<List<Product>?> = _productList

    init {
        _isLoading.value = true
        viewModelScope.launch {
            _sectionList.value = getSectionProductsUseCase()
            _isLoading.value = false
        }
    }

    fun getProductListBySection(sectionId: Int){
        viewModelScope.launch {
           val section = getSectionProductsUseCase().single {
               it.id == sectionId
           }.list
            _productList.value = section
        }
    }
}
