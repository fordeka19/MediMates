package com.medimates.viewModel.groups

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroupsSharedViewModel : ViewModel() {
    val groups = arrayListOf<String>()
    val group = MutableLiveData<String>()
}
