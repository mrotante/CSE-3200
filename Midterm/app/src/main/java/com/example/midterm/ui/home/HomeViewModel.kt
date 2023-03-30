package com.example.midterm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.midterm.Timer

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Timer 1"
    }

    val text: LiveData<String> = _text
}