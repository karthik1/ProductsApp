package com.thikar.products.api

import com.thikar.products.BuildConfig
import com.thikar.products.data.ProductItem
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProductApi {

    companion object {
        const val BASE_URL = "https://22966f1d-9859-4044-a923-ec55f168660e.mock.pstmn.io/api/"
        const val API_KEY = BuildConfig.key
    }

    @Headers("key: $API_KEY")
    @GET("Retailers/4990224/fetchFrequentlyOrderedProduct?retailerId=4990224")
    suspend fun getProductList(): List<ProductItem>

    @Headers("key: $API_KEY")
    @GET("Retailers/4990224/fetchFrequentlyOrderedProduct?retailerId=4990224")
    suspend fun getProductList1(): ProductResponse

}