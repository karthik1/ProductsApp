package com.thikar.products.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thikar.products.R
import com.thikar.products.adapter.ProductListAdapter
import com.thikar.products.data.ProductItem
import com.thikar.products.databinding.FragmentCommonBinding
import com.thikar.products.util.TopSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class FrequentlyOrderedListFragment : Fragment(R.layout.fragment_common) {

    private val viewModel: ProductViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCommonBinding.bind(view)

        val productListAdapter = ProductListAdapter()
        val topSpacingDecorator = TopSpacingItemDecoration(30)

        binding.apply {
            recyclerView.apply {
                val dividerItemDecoration =
                    DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
                addItemDecoration(dividerItemDecoration)
                adapter = productListAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.productList.collect { list ->
                    val freqOrderedList: List<ProductItem> = list.filter { !it.smartRecommendation }
                    if (freqOrderedList.isNotEmpty()) {
                        progressbar.visibility = View.GONE
                    }
                    productListAdapter.submitList(freqOrderedList)
                }
            }
        }
    }
}