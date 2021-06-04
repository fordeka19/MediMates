package com.medimates.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.medimates.MainActivity
import com.medimates.R
import com.medimates.viewModel.groups.GroupsSharedViewModel
import com.medimates.viewModel.home.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private var homeGroupsList: RecyclerView? = null

    private val groupsSharedViewModel: GroupsSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).setActionBarTitle(MEDI_MATES)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeGroupsList = root.findViewById(R.id.groups_recycler_view)
        homeGroupsList?.adapter = HomeAdapter(groupsSharedViewModel.groups, homeViewModel)

        setupGroupsObserver()

        setupFabForCreateGroup(root)
        setupNavigation()

        return root
    }

    private fun setupFabForCreateGroup(root: View) {
        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener { _ ->
            homeViewModel.navigateToCreateGroup()
        }
    }

    private fun setupNavigation() {
        homeViewModel.navDirectionsLiveData.observe(
            viewLifecycleOwner,
            { navDirections ->
                navigate(navDirections)
            }
        )
    }

    private fun setupGroupsObserver() {
        groupsSharedViewModel.groupLiveData.observe(
            viewLifecycleOwner,
            { group ->
                groupsSharedViewModel.groups.add(group)
            }
        )
    }

    private fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }

    companion object {
        const val MEDI_MATES = "MediMates"
    }
}
