package com.medimates.ui.directMessageList

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.medimates.R
import com.medimates.viewModel.home.DirectMessageListViewModel

class DirectMessageListFragment : Fragment() {

    private lateinit var directMessageListViewModel: DirectMessageListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        directMessageListViewModel =
            ViewModelProvider(this).get(DirectMessageListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_direct_message_list, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        directMessageListViewModel.text.observe(
            viewLifecycleOwner,
            Observer {
                textView.text = it
            }
        )

        textView.setOnClickListener { directMessageListViewModel.navigateToDirectMessage(textView.text.toString()) }
        setupNavigation()
        return root
    }

    private fun setupNavigation() {
        directMessageListViewModel.navDirectionsLiveData.observe(
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
