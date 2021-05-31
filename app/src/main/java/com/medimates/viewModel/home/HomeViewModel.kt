package com.medimates.viewModel.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.medimates.liveData.SingleLiveEvent
import com.medimates.ui.home.HomeFragmentDirections

class HomeViewModel : ViewModel() {

    private val _navDirectionsLiveData = SingleLiveEvent<NavDirections>()
    val navDirectionsLiveData = _navDirectionsLiveData

    fun navigateToCreateGroup() {
        _navDirectionsLiveData.value =
            HomeFragmentDirections.actionHomeFragmentToCreateGroupFragment()
    }

    fun navigateToGroupMessagingFragment(groupName: String) {
        _navDirectionsLiveData.value =
            HomeFragmentDirections.actionHomeFragmentToGroupMessagingFragment(groupName)
    }
}
