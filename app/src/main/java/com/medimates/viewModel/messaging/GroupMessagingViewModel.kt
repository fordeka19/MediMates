package com.medimates.viewModel.messaging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroupMessagingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is group messaging fragment"
    }
    val text: LiveData<String> = _text
}
