package com.savchukandrew.macaroncoffeeshop.features.home.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.databinding.FragmentHomeBinding
import com.savchukandrew.macaroncoffeeshop.features.detail.presentation.DetailFragment
import com.savchukandrew.macaroncoffeeshop.features.home.presentation.adapters.ProductAdapter
import com.savchukandrew.macaroncoffeeshop.features.home.presentation.adapters.SectionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ProductAdapter.OnProductClickListener,
    SectionAdapter.OnSectionClickListener {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val adapter = SectionAdapter()

        viewModel.homeState.observe(viewLifecycleOwner) {

            adapter.submitList(it.sectionList)
        }
        binding.parentRecyclerView.adapter = adapter

        adapter.setSectionListener(this)
        adapter.setInnerListener(this)

    }

    override fun onClick(productId: String) {
        findNavController().navigate(R.id.detailFragment, DetailFragment.setProductId(productId))
    }

    override fun onSectionClick(sectionEntitiesId: Int) {
        TODO("Not yet implemented")
    }

}
