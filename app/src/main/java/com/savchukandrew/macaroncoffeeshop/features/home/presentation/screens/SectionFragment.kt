package com.savchukandrew.macaroncoffeeshop.features.home.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.databinding.FragmentSectionBinding
import com.savchukandrew.macaroncoffeeshop.features.home.presentation.HomeViewModel
import com.savchukandrew.macaroncoffeeshop.features.home.presentation.adapters.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectionFragment : Fragment(R.layout.fragment_section) {

    private lateinit var binding: FragmentSectionBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSectionBinding.bind(view)

        val sectionId = requireArguments().getInt(SECTION_ID_EXTRA)
        viewModel.getProductListBySection(sectionId)

        viewModel.productList.observe(viewLifecycleOwner) {
            val adapter = ProductAdapter(it ?: emptyList())
            binding.recycler.adapter = adapter
            binding.recycler.layoutManager = GridLayoutManager(context, 2)
        }
    }

    companion object {
        private const val SECTION_ID_EXTRA = "SECTION_ID_EXTRA"

        fun setSectionId(sectionId: Int): Bundle {
            return bundleOf(SECTION_ID_EXTRA to sectionId)
        }
    }
}