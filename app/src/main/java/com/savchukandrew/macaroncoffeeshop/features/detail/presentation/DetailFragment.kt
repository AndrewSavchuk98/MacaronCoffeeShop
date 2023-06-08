package com.savchukandrew.macaroncoffeeshop.features.detail.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.core.presentation.views.ButtonsAction
import com.savchukandrew.macaroncoffeeshop.core.presentation.views.NumberCounterView
import com.savchukandrew.macaroncoffeeshop.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        var counter = 0
        binding.counter.setListener(object : NumberCounterView.ButtonListener{
            override fun onClick(action: ButtonsAction) {
                when (action) {
                    ButtonsAction.ADD -> {
                        binding.counter.setCounterText((++counter).toString())
                    }
                    ButtonsAction.REMOVE -> {
                        binding.counter.setCounterText((--counter).toString())
                    }
                }
            }

        })

    }
}