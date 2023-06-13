package com.savchukandrew.macaroncoffeeshop.features.home.presentation.screens.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.databinding.FragmentOrderBinding
import com.savchukandrew.macaroncoffeeshop.features.home.presentation.adapters.ProductSectionPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment(R.layout.fragment_order){

    private lateinit var binding: FragmentOrderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderBinding.bind(view)

        val adapter = ProductSectionPagerAdapter(this)
        binding.recyclerViewPager.adapter = adapter
        val tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.recyclerViewPager) { tab, position ->

                tab.text = when (position) {
                    0 -> "Drinks"
                    1 -> "Food"
                    else -> ""
                }

            }
        tabLayoutMediator.attach()
    }
}