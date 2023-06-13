package com.savchukandrew.macaroncoffeeshop.features.home.presentation.screens.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.Product
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.SectionEntities
import com.savchukandrew.macaroncoffeeshop.features.home.domain.usecases.GetDrinksUseCase
import com.savchukandrew.macaroncoffeeshop.features.home.domain.usecases.GetFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val drinksUseCase: GetDrinksUseCase,
    private val foodUseCase: GetFoodUseCase
) : ViewModel() {

    private var _state: MutableLiveData<State?> = MutableLiveData()
    val state: LiveData<State?> = _state

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _isLoading.value = true
        viewModelScope.launch {
            _state.value = State(
                drinkList = drinksUseCase(),
                foodList = foodUseCase()
            )
            _isLoading.value = false
        }
    }
}

data class State(
    var drinkList: List<SectionEntities>,
    var foodList: List<SectionEntities>
)