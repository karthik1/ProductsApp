package com.thikar.products.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thikar.products.data.ProductItem
import com.thikar.products.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val productListFlow = MutableStateFlow<List<ProductItem>>(emptyList())
    val productList: Flow<List<ProductItem>> = productListFlow

    init {
        viewModelScope.launch {
            val list = repository.getProductList()
            list?.let {
                productListFlow.value = list
            }
        }
    }
}