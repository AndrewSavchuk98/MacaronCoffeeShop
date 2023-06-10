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

    private var _homeState = MutableLiveData<HomeState>()
    val homeState: LiveData<HomeState> = _homeState

    init {
        try {
            viewModelScope.launch {

                _homeState.value?.sectionList = getSectionProductsUseCase()
            }

        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}

data class HomeState(
    val productList: List<Product>,
    var sectionList: List<SectionEntities>,
    val isLoading: Boolean
)