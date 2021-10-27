package com.thikar.products.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.thikar.products.R
import com.thikar.products.adapter.FREQUENTLY_ORDERED
import com.thikar.products.adapter.ProductsViewPagerAdapter
import com.thikar.products.adapter.RECOMMENDED
import com.thikar.products.databinding.ActivityProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {

    lateinit var binding:ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = ProductsViewPagerAdapter(this)


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()


    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            FREQUENTLY_ORDERED -> getString(R.string.frequently_ordered)
            RECOMMENDED -> getString(R.string.recommended)
            else -> null
        }
    }
}