package com.savchukandrew.macaroncoffeeshop.features.offer.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.savchukandrew.macaroncoffeeshop.databinding.OfferItemBinding
import com.savchukandrew.macaroncoffeeshop.features.offer.domain.models.OfferItem

class OfferAdapter : ListAdapter<OfferItem, OfferAdapter.OfferViewHolder>(OfferDiffUtil) {

    class OfferViewHolder(private val binding: OfferItemBinding) : ViewHolder(binding.root) {
        fun bind(item: OfferItem) {
            with(binding) {
                productName.text = item.productName
                descriptionTextView.text = item.description
                quantityTextView.text = "x${item.quantity}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = OfferItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

object OfferDiffUtil : DiffUtil.ItemCallback<OfferItem>() {
    override fun areItemsTheSame(oldItem: OfferItem, newItem: OfferItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OfferItem, newItem: OfferItem): Boolean {
        return oldItem == newItem
    }

}