package com.savchukandrew.macaroncoffeeshop.features.detail.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.core.presentation.views.ButtonsAction
import com.savchukandrew.macaroncoffeeshop.core.presentation.views.NumberCounterView
import com.savchukandrew.macaroncoffeeshop.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail), NumberCounterView.ButtonListener {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel by viewModels<ProductDetailViewModel>()
    private var counter = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        binding.counterView.setListener(this)

        binding.iceChipGroup.setOnCheckedChangeListener { chipGroup, checkedId ->
            val titleOrNull = chipGroup.findViewById<Chip>(checkedId)?.text
        }
    }

    override fun onClick(action: ButtonsAction) {
        when (action) {
            ButtonsAction.ADD -> {
                binding.counterView.setCounterText((++counter).toString())
            }

            ButtonsAction.REMOVE -> {
                if (counter > 0)
                    binding.counterView.setCounterText((--counter).toString())
            }
        }
    }
}