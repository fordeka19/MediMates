package com.medimates.viewModel.groups

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.medimates.liveData.SingleLiveEvent
import com.medimates.ui.createGroup.CreateGroupFragmentDirections

class CreateGroupViewModel : ViewModel() {

    private val _navDirectionsLiveData = SingleLiveEvent<NavDirections>()
    val navDirectionsLiveData = _navDirectionsLiveData

    fun navigateToAddPeople(groupName: String) {
        _navDirectionsLiveData.value =
            CreateGroupFragmentDirections.actionCreateGroupFragmentToAddPeopleFragment(groupName)
    }
}
