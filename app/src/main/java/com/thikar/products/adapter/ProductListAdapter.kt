package com.thikar.products.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

import com.thikar.products.api.ProductResponse
import com.thikar.products.data.ProductItem
import com.thikar.products.databinding.LayoutProductItemBinding

class ProductListAdapter:ListAdapter<ProductItem,ViewHolder>(ListComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        Log.d("TAG", "onBindViewHolder: $currentItem")
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}