package com.savchukandrew.macaroncoffeeshop.features.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.savchukandrew.macaroncoffeeshop.core.presentation.load
import com.savchukandrew.macaroncoffeeshop.databinding.ProductItemBinding
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.Product

class ProductAdapter(private val list: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private lateinit var listener: OnProductClickListener

    fun setOnClickListener(listener: OnProductClickListener) {
        this.listener = listener
    }

    class ProductViewHolder(private val binding: ProductItemBinding) : ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.titleItemTextView.text = product.title
            binding.itemImage.load(product.imageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    interface OnProductClickListener {
        fun onClick(productId: String)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener.onClick(list[position].id)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}