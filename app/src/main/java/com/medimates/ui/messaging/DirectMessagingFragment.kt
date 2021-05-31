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
import com.medimates.viewModel.messaging.DirectMessagingViewModel

class DirectMessagingFragment : Fragment() {

    private lateinit var directMessagingViewModel: DirectMessagingViewModel

    private val args by navArgs<DirectMessagingFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        directMessagingViewModel =
            ViewModelProvider(this).get(DirectMessagingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_direct_messaging, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        directMessagingViewModel.text.observe(
            viewLifecycleOwner,
            Observer {
                textView.text = it
            }
        )

        val toolbar = activity?.findViewById(R.id.toolbar) as Toolbar
        toolbar.title = args.name

        return root
    }
}
