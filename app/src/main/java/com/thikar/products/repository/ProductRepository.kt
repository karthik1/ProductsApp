package com.thikar.products.repository

import android.util.Log
import com.thikar.products.api.ProductApi
import com.thikar.products.data.ProductItem
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

}