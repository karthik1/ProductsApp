package com.thikar.products.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thikar.products.ui.FrequentlyOrderedListFragment
import com.thikar.products.ui.RecommendedListFragment

const val FREQUENTLY_ORDERED = 0
const val RECOMMENDED = 1

class ProductsViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        FREQUENTLY_ORDERED to { FrequentlyOrderedListFragment() },
        RECOMMENDED to { RecommendedListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}