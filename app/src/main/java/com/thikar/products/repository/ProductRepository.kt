package com.thikar.products.repository

import com.thikar.products.api.ProductApi
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productApi: ProductApi,
) {

}