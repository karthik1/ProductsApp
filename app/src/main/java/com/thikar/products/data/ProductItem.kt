package com.thikar.products.data

data class ProductItem(
    val productName:String,
    val displayText:String,
    val schemeLabelForRetailer:String,
    val distributorName:String,
    val manufacturerName:String,
    val mrp:Float,
    val ptr:Float,
    val stock:Int,
    val productUrl:String,
    val smartRecommendation:Boolean
)