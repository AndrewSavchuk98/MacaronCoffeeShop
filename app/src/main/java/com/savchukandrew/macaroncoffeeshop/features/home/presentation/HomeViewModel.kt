package com.savchukandrew.macaroncoffeeshop.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.Product
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.SectionEntities
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private var _homeState = MutableLiveData<HomeState>()
    val homeState: LiveData<HomeState> = _homeState

}

data class HomeState(
    val productList: List<Product>,
    val sectionList: List<SectionEntities>,
    val isLoading: Boolean
)