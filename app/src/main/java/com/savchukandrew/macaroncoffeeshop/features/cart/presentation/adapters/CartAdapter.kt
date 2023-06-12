package com.savchukandrew.macaroncoffeeshop.features.cart.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.savchukandrew.macaroncoffeeshop.core.presentation.load
import com.savchukandrew.macaroncoffeeshop.databinding.CartItemBinding
import com.savchukandrew.macaroncoffeeshop.features.cart.domain.CartItem

class CartAdapter : ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffUtil()) {

    class CartViewHolder(private val binding: CartItemBinding) : ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {
            binding.image.load(cartItem.image)
            binding.nameTextView.text = cartItem.productName
            binding.priceTextView.text = "${cartItem.price} grn"
            binding.descriptionTextView.text = cartItem.description
            binding.quantityTextView.setCounterText(cartItem.quantity.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class CartDiffUtil : DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem.id == newItem.id
    }

}