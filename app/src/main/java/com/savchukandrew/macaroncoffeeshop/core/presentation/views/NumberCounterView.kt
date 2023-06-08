package com.savchukandrew.macaroncoffeeshop.core.presentation.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.savchukandrew.macaroncoffeeshop.R
import com.savchukandrew.macaroncoffeeshop.databinding.NumberCountViewBinding

enum class ButtonsAction {
    REMOVE, ADD
}

class NumberCounterView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private lateinit var listener: ButtonListener

    interface ButtonListener {
        fun onClick(action: ButtonsAction)
    }

    private var binding: NumberCountViewBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.number_count_view, this, true)
        binding = NumberCountViewBinding.bind(this)
        initAttributes(attrs, defStyleAttr, defStyleRes)
        initButtonsListener()
    }

    private fun initAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.NumberCounterView,
            defStyleAttr,
            defStyleRes
        )

        val buttonsColor =
            typedArray.getColor(R.styleable.NumberCounterView_buttonBackgroundColor, Color.WHITE)
        binding.addButton.backgroundTintList = ColorStateList.valueOf(buttonsColor)
        binding.removeButton.backgroundTintList = ColorStateList.valueOf(buttonsColor)

        typedArray.recycle()
    }

    private fun initButtonsListener() {
        binding.addButton.setOnClickListener {
            listener.onClick(ButtonsAction.ADD)
        }
        binding.removeButton.setOnClickListener {
            listener.onClick(ButtonsAction.REMOVE)
        }
    }

    fun setListener(listener: ButtonListener) {
        this.listener = listener
    }

    fun setCounterText(text: String) {
        binding.counterText.text = text
    }
}