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
import com.thikar.products.util.Resource
import com.thikar.products.util.exhaustive
import com.thikar.products.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.annotation.meta.Exhaustive


@AndroidEntryPoint
class FrequentlyOrderedListFragment : Fragment(R.layout.fragment_common) {

    private val viewModel: ProductViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCommonBinding.bind(view)
        val productListAdapter = ProductListAdapter()

        binding.apply {
            recyclerView.apply {
                val dividerItemDecoration =
                    DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
                addItemDecoration(dividerItemDecoration)
                adapter = productListAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
//
//            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//                viewModel.productList.collect { list ->
////                    progressbar.visibility = result is Resource.Loading
//
//                    val freqOrderedList: List<ProductItem> = list.filter { !it.smartRecommendation }
//                    if (freqOrderedList.isNotEmpty()) {
//                        progressbar.visibility = View.GONE
//                    }
//                    productListAdapter.submitList(freqOrderedList)
//                }
//            }

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.productList1.collect {
                    val result = it ?: return@collect
                    if(result is Resource.Loading)
                    {
                        progressbar.visibility = View.VISIBLE
                    }
                    else{
                        progressbar.visibility = View.GONE
                    }

                    val freqOrderedList: List<ProductItem> = result?.data!!.filter { !it.smartRecommendation }
                    if(freqOrderedList.isNotEmpty()) {
                        productListAdapter.submitList(freqOrderedList)
                    }
                    else {
                        textviewEmptyLabel.visibility = View.VISIBLE
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.events.collect { event ->
                    when (event) {
                        is ProductViewModel.Event.ShowErrorMessage ->
                        {
                            showSnackbar(
                                getString(
                                    R.string.could_not_fetch
                                )
                            )

                            textviewEmptyLabel.visibility = View.VISIBLE
                            progressbar.visibility = View.GONE
                        }
                    }.exhaustive
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }
}