package com.medimates.ui.createGroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.medimates.R
import com.medimates.viewModel.groups.CreateGroupViewModel
import com.medimates.viewModel.groups.GroupsSharedViewModel

class CreateGroupFragment : Fragment() {

    private lateinit var createGroupViewModel: CreateGroupViewModel
    private var nameEditText: EditText? = null
    private val groupsSharedViewModel: GroupsSharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createGroupViewModel =
            ViewModelProvider(this).get(CreateGroupViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_create_group, container, false)
        nameEditText = root.findViewById(R.id.create_group_edittext)
        setupNavigation()
        return root
    }
// TODO GREY OUT CREATE BUTTON WHEN TITLE IS EMPTY !!!!
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_group_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.create_group) {
            val groupName = nameEditText?.text.toString()
            groupsSharedViewModel.group.postValue(groupName)
            createGroupViewModel.navigateToAddPeople(groupName)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNavigation() {
        createGroupViewModel.navDirectionsLiveData.observe(
            viewLifecycleOwner,
            { navDirections ->
                navigate(navDirections)
            }
        )
    }

    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }
}
