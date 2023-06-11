package com.savchukandrew.macaroncoffeeshop.features.home.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.savchukandrew.macaroncoffeeshop.features.home.presentation.screens.order.ProductRecyclerFragment

class ProductSectionPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return ProductRecyclerFragment.newInstance(position)
    }
}