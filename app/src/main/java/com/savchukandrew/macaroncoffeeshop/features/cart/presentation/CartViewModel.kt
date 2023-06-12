package com.savchukandrew.macaroncoffeeshop.features.cart.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savchukandrew.macaroncoffeeshop.features.cart.domain.CartItem
import com.savchukandrew.macaroncoffeeshop.features.cart.domain.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    private val _cartItemList: MutableLiveData<List<CartItem>> = MutableLiveData()
    val cartItemList: LiveData<List<CartItem>> = _cartItemList

    init {
        viewModelScope.launch {
            _cartItemList.value = repository.readData()
        }
    }

    fun setData(cartItem: CartItem) {
        val list = mutableListOf<CartItem>()
        list.add(cartItem)
        viewModelScope.launch {
            repository.saveData(list.toList())
        }
    }
}