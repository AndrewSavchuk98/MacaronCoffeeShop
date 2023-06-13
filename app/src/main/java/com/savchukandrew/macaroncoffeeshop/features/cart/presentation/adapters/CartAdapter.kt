package com.savchukandrew.macaroncoffeeshop.features.cart.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.savchukandrew.macaroncoffeeshop.core.presentation.load
import com.savchukandrew.macaroncoffeeshop.core.presentation.views.ButtonsAction
import com.savchukandrew.macaroncoffeeshop.core.presentation.views.NumberCounterView
import com.savchukandrew.macaroncoffeeshop.databinding.CartItemBinding
import com.savchukandrew.macaroncoffeeshop.features.cart.domain.CartItem

class CartAdapter(
    private val listener: OnRemoteButtonClick
) : ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffUtil) {

    interface OnRemoteButtonClick {
        fun onRemoteClick(cartItem: CartItem)
    }

    class CartViewHolder(
        private val binding: CartItemBinding
    ) : ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {
            binding.deleteButton.tag = cartItem

            binding.image.load(cartItem.image)
            binding.nameTextView.text = cartItem.productName
            binding.priceTextView.text = "${cartItem.price} grn"
            binding.descriptionTextView.text = cartItem.description
            binding.quantityTextView.setCounterText(cartItem.quantity.toString())
            binding.quantityTextView.setListener(object : NumberCounterView.ButtonListener {
                override fun onClick(action: ButtonsAction) {
                    when (action) {
                        ButtonsAction.ADD -> {
                            cartItem.quantity++
                            binding.quantityTextView.setCounterText((cartItem.quantity).toString())
                        }

                        ButtonsAction.REMOVE -> {
                            if (cartItem.quantity > 0) {
                                cartItem.quantity--
                                binding.quantityTextView.setCounterText((cartItem.quantity).toString())
                            }
                        }
                    }
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.deleteButton.setOnClickListener {
            val cartItem = it.tag as CartItem
            listener.onRemoteClick(cartItem)
        }
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

object CartDiffUtil : DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem == newItem
    }

}