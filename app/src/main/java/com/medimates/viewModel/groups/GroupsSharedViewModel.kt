package com.medimates.viewModel.groups

import androidx.lifecycle.ViewModel
import com.medimates.liveData.SingleLiveEvent

class GroupsSharedViewModel : ViewModel() {
    val groupLiveData = SingleLiveEvent<String>()
    val groups = arrayListOf<String>()
}
