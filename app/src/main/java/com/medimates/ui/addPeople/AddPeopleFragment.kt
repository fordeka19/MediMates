package com.medimates.ui.addPeople

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.medimates.R
import com.medimates.viewModel.home.AddPeopleViewModel

class AddPeopleFragment : Fragment() {

    private lateinit var addPeopleViewModel: AddPeopleViewModel

    private val args by navArgs<AddPeopleFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addPeopleViewModel = ViewModelProvider(this).get(AddPeopleViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_people, container, false)
        setupNavigation()
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_people_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.skip) {
            addPeopleViewModel.navigateToGroupMessaging(args.groupName)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNavigation() {
        addPeopleViewModel.navDirectionsLiveData.observe(
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
