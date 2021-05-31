package com.medimates.viewModel.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.medimates.liveData.SingleLiveEvent
import com.medimates.ui.addPeople.AddPeopleFragmentDirections

class AddPeopleViewModel : ViewModel() {

    private val _navDirectionsLiveData = SingleLiveEvent<NavDirections>()
    val navDirectionsLiveData = _navDirectionsLiveData

    fun navigateToGroupMessaging(groupName: String) {
        _navDirectionsLiveData.value =
            AddPeopleFragmentDirections.actionAddPeopleFragmentToGroupMessagingFragment(groupName)
    }
}
