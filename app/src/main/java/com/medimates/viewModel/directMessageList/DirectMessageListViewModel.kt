package com.medimates.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.medimates.liveData.SingleLiveEvent
import com.medimates.ui.directMessageList.DirectMessageListFragmentDirections

class DirectMessageListViewModel : ViewModel() {

    private val _navDirectionsLiveData = SingleLiveEvent<NavDirections>()
    val navDirectionsLiveData = _navDirectionsLiveData

    private val _text = MutableLiveData<String>().apply {
        value = "This is direct message list Fragment"
    }
    val text: LiveData<String> = _text

    fun navigateToDirectMessage(name: String) {
        _navDirectionsLiveData.value =
            DirectMessageListFragmentDirections.actionDirectMessageListFragmentToDirectMessagingFragment(name)
    }
}
