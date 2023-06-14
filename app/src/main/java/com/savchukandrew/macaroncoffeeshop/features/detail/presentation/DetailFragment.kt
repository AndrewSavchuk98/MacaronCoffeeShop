package com.savchukandrew.macaroncoffeeshop.features.detail.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.chip.Chip
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.core.presentation.views.ButtonsAction
import com.savchukandrew.macaroncoffeeshop.core.presentation.views.NumberCounterView
import com.savchukandrew.macaroncoffeeshop.databinding.FragmentDetailBinding
import com.savchukandrew.macaroncoffeeshop.features.cart.domain.CartItem
import com.savchukandrew.macaroncoffeeshop.features.cart.presentation.CartFragment
import com.savchukandrew.macaroncoffeeshop.features.detail.domain.model.DescriptionModel
import com.savchukandrew.macaroncoffeeshop.features.detail.domain.model.ProductDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail), NumberCounterView.ButtonListener {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel by viewModels<ProductDetailViewModel>()
    private var counter = 1
    private lateinit var product: ProductDetail
    private var totalPrice = 0
    private val descriptionModel = DescriptionModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        binding.counterView.setListener(this)
        val productId = requireArguments().getString(PRODUCT_ID_EXTRA, "")
        viewModel.getProductById(productId)

        binding.iceChipGroup.setOnCheckedChangeListener { chipGroup, checkedId ->
            val titleOrNull = chipGroup.findViewById<Chip>(checkedId)?.text
            descriptionModel.ice = titleOrNull.toString()
        }
        binding.sizeChipGroup.setOnCheckedChangeListener { chipGroup, checkedId ->
            val titleOrNull = chipGroup.findViewById<Chip>(checkedId)?.text
            val total = when (titleOrNull) {
                "Regular" -> product.price - 5
                "Medium" -> product.price
                "Large" -> product.price + 10
                else -> product.price
            }
            binding.priceTextView.text = "$total grn"
            updateTotalPrice(total)
            // updatePrice(product.price)
            descriptionModel.size = titleOrNull.toString()
        }
        binding.sugarChipGroup.setOnCheckedChangeListener { chipGroup, checkedId ->
            val titleOrNull = chipGroup.findViewById<Chip>(checkedId)?.text
            //customizeHashMap[chipGroup.id] = titleOrNull.toString()
            descriptionModel.sugar = titleOrNull.toString()
        }
        binding.variantChipGroup.setOnCheckedChangeListener { chipGroup, checkedId ->
            val titleOrNull = chipGroup.findViewById<Chip>(checkedId)?.text
            //  customizeHashMap[chipGroup.id] = titleOrNull.toString()
            descriptionModel.variant = titleOrNull.toString()
        }

        viewModel.productDetail.observe(viewLifecycleOwner) {
            product = it ?: ProductDetail("", "", "", "", 0, "")
            binding.productName.text = it?.name
            binding.imageBackground.load(it?.image ?: "")
            binding.productTitle.text = it?.categoryId
            binding.productDescriptionTextView.text = it?.description
            binding.priceTextView.text = "${it?.price} grn"
            updatePrice(it?.price ?: 0)
        }

        binding.addOrderButton.setOnClickListener {
            findNavController().navigate(
                R.id.cartFragment, CartFragment.setArgs(
                    CartItem(
                        id = productId,
                        image = product.image,
                        productName = product.name,
                        description = "Size: ${descriptionModel.size}, " +
                                "variant: ${descriptionModel.variant}, \n" +
                                "Sugar: ${descriptionModel.sugar}, " +
                                "ice: ${
                                    if (descriptionModel.variant == "Hot") {
                                        "None"
                                    } else {
                                        descriptionModel.ice
                                    }
                                }",
                        price = totalPrice,
                        quantity = counter
                    )
                )
            )
        }


        binding.counterView.setCounterText(counter.toString())
    }

    override fun onClick(action: ButtonsAction) {
        when (action) {
            ButtonsAction.ADD -> {
                increaseQuantity()
            }

            ButtonsAction.REMOVE -> {
                decreaseQuantity()
            }
        }
    }

    private fun increaseQuantity() {
        counter++
        binding.counterView.setCounterText((counter).toString())
        updateTotalPrice(product.price)
    }

    private fun decreaseQuantity() {
        if (counter > 0) {
            counter--
            binding.counterView.setCounterText((counter).toString())
            //updateTotalPrice()
            updateTotalPrice(product.price)
        }
    }

    private fun updateTotalPrice() {
        val total = product.price * counter
        updatePrice(total)
    }

    private fun updateTotalPrice(price: Int) {
        val total = price * counter
        updatePrice(total)
    }

    private fun updatePrice(price: Int) {
        binding.totalPriceTextView.text = "$price grn"
        totalPrice = price
    }

    private fun updateUI() {
        // binding.totalPriceTextView.text = "$price grn"
        //binding.priceTextView.text = "${it?.price} grn"

    }

    companion object {
        private const val PRODUCT_ID_EXTRA =
            "com.savchukandrew.macaroncoffeeshop.features.detail.presentation.PRODUCT_ID_EXTRA"

        fun setProductId(productId: String): Bundle {
            return bundleOf(PRODUCT_ID_EXTRA to productId)
        }
    }
}