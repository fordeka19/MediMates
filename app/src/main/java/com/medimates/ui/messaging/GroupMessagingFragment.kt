package com.medimates.ui.messaging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.medimates.R
import com.medimates.viewModel.messaging.GroupMessagingViewModel

class GroupMessagingFragment : Fragment() {

    private lateinit var groupMessagingViewModel: GroupMessagingViewModel

    private val args by navArgs<GroupMessagingFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupMessagingViewModel =
            ViewModelProvider(this).get(GroupMessagingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_group_messaging, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)

        val toolbar = activity?.findViewById(R.id.toolbar) as Toolbar
        toolbar.title = args.groupName

        groupMessagingViewModel.text.observe(
            viewLifecycleOwner,
            Observer {
                textView.text = it
            }
        )
        return root
    }
}
