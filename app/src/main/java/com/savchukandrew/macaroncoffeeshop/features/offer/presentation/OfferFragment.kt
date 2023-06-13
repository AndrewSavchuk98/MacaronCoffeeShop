package com.savchukandrew.macaroncoffeeshop.features.offer.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.databinding.FragmentOfferBinding
import com.savchukandrew.macaroncoffeeshop.features.offer.presentation.adapters.OfferAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OfferFragment : Fragment(R.layout.fragment_offer) {

    private lateinit var binding: FragmentOfferBinding

    private val viewModel by viewModels<OfferViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOfferBinding.bind(view)

        val adapter = OfferAdapter()
        binding.offerRecycler.adapter = adapter
    }
}