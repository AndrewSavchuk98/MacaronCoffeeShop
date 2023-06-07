package com.savchukandrew.macaroncoffeeshop.features.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.savchukandrew.macaroncoffeeshop.databinding.SectionItemBinding
import com.savchukandrew.macaroncoffeeshop.features.home.domain.models.SectionEntities

class SectionAdapter : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {

    private var list: List<SectionEntities> = emptyList()
    private lateinit var innerListener: ProductAdapter.OnProductClickListener
    private lateinit var sectionListener: OnSectionClickListener
    fun submitList(list: List<SectionEntities>) {
        this.list = list
    }

    fun setSectionListener(listener: OnSectionClickListener) {
        sectionListener = listener
    }

    interface OnSectionClickListener {
        fun onSectionClick(sectionEntities: SectionEntities)
    }

    fun setInnerListener(listener: ProductAdapter.OnProductClickListener) {
        innerListener = listener
    }

    class SectionViewHolder(
        private val binding: SectionItemBinding,
        private val sectionListener: OnSectionClickListener,
        private val listener: ProductAdapter.OnProductClickListener
    ) : ViewHolder(binding.root) {
        fun bind(sectionEntities: SectionEntities) {
            binding.titleTextView.text = sectionEntities.title
            binding.moreButton.setOnClickListener {
                sectionListener.onSectionClick(sectionEntities)
            }
            val adapter = ProductAdapter(sectionEntities.list)
            binding.productRecyclerView.adapter = adapter
            adapter.setOnClickListener(listener = listener)
            binding.productRecyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val binding = SectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionViewHolder(binding, sectionListener, innerListener)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        if (list.isNotEmpty()) {
            holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}