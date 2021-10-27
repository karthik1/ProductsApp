package com.thikar.products.adapter

import androidx.recyclerview.widget.DiffUtil
import com.thikar.products.data.ProductItem

class ListComparator : DiffUtil.ItemCallback<ProductItem>() {

    override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem) =
        oldItem.stock == newItem.stock

    override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem) =
        oldItem == newItem
}