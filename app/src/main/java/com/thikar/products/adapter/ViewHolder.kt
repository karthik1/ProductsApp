package com.thikar.products.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thikar.products.R
import com.thikar.products.data.ProductItem
import com.thikar.products.databinding.LayoutProductItemBinding

class ViewHolder(
    private val binding: LayoutProductItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ProductItem) = with(binding.root) {

        binding.apply {
            Glide.with(itemView)
                .load(item.productUrl)
                .error(R.drawable.image_placeholder)
                .into(imageviewItem)

            textviewDisplayText.text = item.displayText
            textviewProductName.text = item.productName
            textviewSchemeLabel.text = item.schemeLabelForRetailer
            textviewDistributor.text = item.distributorName
            textviewManufacturer.text = item.manufacturerName

        }
        binding.textviewPtr.text = context.getString(R.string.Rs, item.ptr.toString())
        binding.textviewMrp.text = context.getString(R.string.Rs, item.mrp.toString())
        binding.textviewStock.text = context.getString(R.string.append_string_with_space, item.stock.toString())
    }
}