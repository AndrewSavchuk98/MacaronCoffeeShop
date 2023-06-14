package com.savchukandrew.macaroncoffeeshop.features.offer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savchukandrew.macaroncoffeeshop.features.cart.domain.CartItem
import com.savchukandrew.macaroncoffeeshop.features.cart.domain.CartRepository
import com.savchukandrew.macaroncoffeeshop.features.offer.domain.models.OfferItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfferViewModel @Inject constructor(
    cartRepository: CartRepository
) : ViewModel() {

    private val _offerList: MutableLiveData<List<OfferItem>> = MutableLiveData()
    val offerList: LiveData<List<OfferItem>> = _offerList
    private val _totalPrice :MutableLiveData<Int> = MutableLiveData()
    val totalPrice: LiveData<Int> = _totalPrice
    private var price = 0
    init {
        viewModelScope.launch {
            cartRepository.readData().collect { list ->
                _offerList.value = list.map {
                    mapper(it)
                }
                list.forEach {
                   price += it.price
                }
            }
            _totalPrice.value = price
        }
    }


    private fun mapper(item: CartItem): OfferItem {
        return OfferItem(
            id = item.id,
            productName = item.productName,
            description = item.description,
            quantity = item.quantity
        )
    }

}