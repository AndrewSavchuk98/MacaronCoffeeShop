package com.savchukandrew.macaroncoffeeshop.features.cart.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.databinding.FragmentCartBinding
import com.savchukandrew.macaroncoffeeshop.features.cart.domain.CartItem
import com.savchukandrew.macaroncoffeeshop.features.cart.presentation.adapters.CartAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var binding: FragmentCartBinding

    private val viewModel by viewModels<CartViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)

        val adapter = CartAdapter()
        binding.cartRecycler.adapter = adapter

        val cartItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(CART_ITEM_EXTRA, CartItem::class.java)
        } else {
            requireArguments().getParcelable(CART_ITEM_EXTRA)
        }

        viewModel.setData(cartItem!!)

        viewModel.cartItemList.observe(viewLifecycleOwner) {
            adapter.submitList(it)

        }

        binding.addMoreButton.setOnClickListener {
            findNavController().navigate(R.id.orderFragment)
        }
        binding.makeOfferButton.setOnClickListener {

        }
    }


    companion object {
        private const val CART_ITEM_EXTRA = "CART_ITEM_EXTRA"

        fun setArgs(cartItem: CartItem): Bundle {
            return bundleOf(
                CART_ITEM_EXTRA to cartItem
            )
        }
    }
}