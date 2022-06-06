package com.example.nycschools.common

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("shouldShow")
fun bindShouldShowTextView(view: TextView, text: String?) {
    view.apply {
        visibility = if (text.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
        setText(text)
    }
}