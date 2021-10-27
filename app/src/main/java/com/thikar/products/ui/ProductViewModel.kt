package com.thikar.products.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thikar.products.data.ProductItem
import com.thikar.products.repository.ProductRepository
import com.thikar.products.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val eventChannel = Channel<Event>()
    val events = eventChannel.receiveAsFlow()

    private val refreshTriggerChannel = Channel<Unit>()
    private val refreshTrigger = refreshTriggerChannel.receiveAsFlow()

    val productList1 = refreshTrigger.flatMapLatest {
       repository.getList {
           viewModelScope.launch {
               Log.d("TAG", "getList: Error failed")
               eventChannel.send(Event.ShowErrorMessage(it))
           }
       }
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    private val productListFlow = MutableStateFlow<List<ProductItem>>(emptyList())
    val productList: Flow<List<ProductItem>> = productListFlow

//    init {
//        viewModelScope.launch {
//
//            val productList = repository.getList {
//                viewModelScope.launch { eventChannel.send(Event.ShowErrorMessage(it)) }
//            }
//
//            val list = repository.getProductList()
//            list?.let {
//                productListFlow.value = list
//            }
//        }
//    }

    fun onStart() {
        if (productList1.value !is Resource.Loading) {
            viewModelScope.launch {
                refreshTriggerChannel.send(Unit)
            }
        }
    }

    sealed class Event {
        data class ShowErrorMessage(val error: Throwable) : Event()
    }
}