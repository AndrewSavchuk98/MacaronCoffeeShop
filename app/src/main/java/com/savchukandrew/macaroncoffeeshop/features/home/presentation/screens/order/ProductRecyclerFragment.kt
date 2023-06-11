package com.savchukandrew.macaroncoffeeshop.features.home.presentation.screens.order

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.databinding.FragmentHomeBinding
import com.savchukandrew.macaroncoffeeshop.features.detail.presentation.DetailFragment
import com.savchukandrew.macaroncoffeeshop.features.home.presentation.HomeViewModel
import com.savchukandrew.macaroncoffeeshop.features.home.presentation.adapters.ProductAdapter
import com.savchukandrew.macaroncoffeeshop.features.home.presentation.adapters.SectionAdapter
import com.savchukandrew.macaroncoffeeshop.features.home.presentation.screens.SectionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductRecyclerFragment : Fragment(R.layout.fragment_home),
    ProductAdapter.OnProductClickListener,
    SectionAdapter.OnSectionClickListener {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val sectionAdapter = SectionAdapter()
        binding.parentRecyclerView.adapter = sectionAdapter
        sectionAdapter.setSectionListener(this)
        sectionAdapter.setInnerListener(this)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
                binding.parentRecyclerView.adapter = sectionAdapter
            }
        }
        viewModel.sectionList.observe(viewLifecycleOwner) {
            val list = when (requireArguments().getInt(TAB_POSITION_KEY)) {
                0 -> it?.shuffled()
                1 -> it?.shuffled()
                else -> emptyList()
            }
            sectionAdapter.submitList(list ?: emptyList())
        }

    }

    override fun onClick(productId: String) {
        findNavController().navigate(R.id.detailFragment, DetailFragment.setProductId(productId))
    }

    override fun onSectionClick(sectionEntitiesId: Int) {
        findNavController().navigate(
            R.id.sectionFragment,
            SectionFragment.setSectionId(sectionEntitiesId)
        )
    }

    companion object {

        private const val TAB_POSITION_KEY = "tab_position"

        fun newInstance(tabPosition: Int): Fragment {
            val fragment = ProductRecyclerFragment()
            fragment.arguments = bundleOf(TAB_POSITION_KEY to tabPosition)
            return fragment
        }

    }
}