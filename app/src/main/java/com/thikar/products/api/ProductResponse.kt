package com.thikar.products.api

import com.thikar.products.data.ProductItem

data class ProductResponse(val productItemList:List<ProductItem>)

// SAMPLE RESPONSE

//"productName": "GLYCIPHAGE SR 1GM TAB",
//"displayText": "0 in tab",
//"schemeLabelForRetailer": "No Schemes",
//"distributorName": "B.K Distributor, Ahmedabad",
//"manufacturerName": "FRANCO INDIA",
//"mrp": 40.85,
//"ptr": 29.18,
//"stock": 1224,
//"productUrl": "https://d1bbamdfgjpmwd.cloudfront.net/dam/products/085695/glyciphage-sr-1gm-strip-of-10-tablets-front-2-1597365345.jpg?watermark=retailio",
//"smartRecommendation": false