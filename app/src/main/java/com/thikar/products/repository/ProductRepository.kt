package com.thikar.products.repository

import android.util.Log
import com.thikar.products.api.ProductApi
import com.thikar.products.data.ProductItem
import com.thikar.products.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productApi: ProductApi
) {
    suspend fun getProductList(): List<ProductItem>? {

        return try {
            val response = productApi.getProductList()
            response
        } catch (t: Throwable) {
            null
        }
    }

    suspend fun getList(onFetchFailed: (Throwable) -> Unit): Flow<Resource<List<ProductItem>>> =
        flow {
            Log.d("TAG", "getList: ")
            var response: List<ProductItem> = emptyList()
            emit(Resource.Loading(response))
            Log.d("TAG", "getList: After loading")

            try {
                response = productApi.getProductList()
                emit(Resource.Success(response))
                Log.d("TAG", "getList: After Succes")
            } catch (t: Throwable) {
                onFetchFailed.invoke(t)
                Log.d("TAG", "getList: After Failure")
                emit(Resource.Error(t, response))
            }
        }

}