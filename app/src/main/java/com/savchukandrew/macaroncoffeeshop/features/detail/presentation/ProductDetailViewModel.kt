package com.savchukandrew.macaroncoffeeshop.features.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savchukandrew.macaroncoffeeshop.features.detail.domain.model.ProductDetail
import com.savchukandrew.macaroncoffeeshop.features.detail.domain.usecases.GetProductDetailById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductDetailState(
    var productDetail: ProductDetail,
    val isLoading: Boolean
)

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailById
) : ViewModel() {

    private val _productDetailState: MutableLiveData<ProductDetailState> = MutableLiveData()
    val productDetailState: LiveData<ProductDetailState> = _productDetailState

    fun getProductById(productId: String) {
        try {
            viewModelScope.launch {
                _productDetailState.value?.productDetail = getProductDetailUseCase(productId)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}