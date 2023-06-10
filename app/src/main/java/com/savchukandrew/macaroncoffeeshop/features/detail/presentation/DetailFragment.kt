package com.savchukandrew.macaroncoffeeshop.features.detail.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.core.presentation.views.ButtonsAction
import com.savchukandrew.macaroncoffeeshop.core.presentation.views.NumberCounterView
import com.savchukandrew.macaroncoffeeshop.databinding.FragmentDetailBinding
import com.savchukandrew.macaroncoffeeshop.features.detail.domain.model.ProductDetail
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

        viewModel.productDetail.observe(viewLifecycleOwner){
            binding.productName.text = it?.name
        }

        val productId = requireArguments().getString(PRODUCT_ID_EXTRA, "")
        viewModel.getProductById(productId)

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

    companion object {
        private const val PRODUCT_ID_EXTRA =
            "com.savchukandrew.macaroncoffeeshop.features.detail.presentation.PRODUCT_ID_EXTRA"

        fun setProductId(productId: String): Bundle {
            return bundleOf(PRODUCT_ID_EXTRA to productId)
        }
    }
}