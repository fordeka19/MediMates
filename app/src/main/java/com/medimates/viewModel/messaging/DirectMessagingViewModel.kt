package com.medimates.viewModel.messaging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DirectMessagingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is direct messaging Fragment"
    }
    val text: LiveData<String> = _text
}
