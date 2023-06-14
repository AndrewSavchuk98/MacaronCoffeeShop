package com.savchukandrew.macaroncoffeeshop.features.offer.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.databinding.FragmentOfferBinding
import com.savchukandrew.macaroncoffeeshop.features.offer.presentation.adapters.OfferAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class OfferFragment : Fragment(R.layout.fragment_offer) {

    private lateinit var binding: FragmentOfferBinding

    private val viewModel by viewModels<OfferViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOfferBinding.bind(view)

        val adapter = OfferAdapter()
        binding.offerRecycler.adapter = adapter
        setDateTime()

        viewModel.offerList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.totalPrice.observe(viewLifecycleOwner){
            binding.totalPriceTextView.text = "$it grn"
        }
    }

    private fun setDateTime() {
        val currentDate = Date()
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val formattedDate = formatter.format(currentDate)
        binding.dateTextView.text = formattedDate

        val currentTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatterTime = DateTimeFormatter.ofPattern("hh.mm")
        val formattedTime = currentTime.format(formatterTime)
        binding.timeTextView.text = formattedTime
    }
}