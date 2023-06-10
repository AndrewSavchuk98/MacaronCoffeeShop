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


@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailById
) : ViewModel() {

    private val _productDetail: MutableLiveData<ProductDetail?> = MutableLiveData()
    val productDetail: LiveData<ProductDetail?> = _productDetail

    fun getProductById(productId: String) {
        viewModelScope.launch {
            _productDetail.value = getProductDetailUseCase(productId)
        }
    }

}