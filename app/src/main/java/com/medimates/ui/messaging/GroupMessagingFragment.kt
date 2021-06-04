package com.medimates.ui.messaging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medimates.R
import com.medimates.model.Message
import com.medimates.model.User
import com.medimates.viewModel.messaging.GroupMessagingViewModel

class GroupMessagingFragment : Fragment() {

    private lateinit var groupMessagingViewModel: GroupMessagingViewModel

    private var mMessageRecyclerView: RecyclerView? = null
    private var mMessageAdapter: MessageListAdapter? = null

    private val args by navArgs<GroupMessagingFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupMessagingViewModel =
            ViewModelProvider(this).get(GroupMessagingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_group_messaging, container, false)

        val toolbar = activity?.findViewById(R.id.toolbar) as Toolbar
        toolbar.title = args.groupName

        mMessageRecyclerView = root.findViewById(R.id.messages_recycler_view)
        activity?.let {
            mMessageAdapter = MessageListAdapter(
                it,
                listOf(
                    Message("test", User("userId", "nickname", "email@email.com"), 1622802926),
                    Message("test", User("me", "nickname2", "email@email.com"), 1622802926), Message("test", User("userId", "nickname", "email@email.com"), 1622722141),
                    Message("test", User("me", "nickname2", "email@email.com"), 1622722141),
                    Message("test", User("me", "nickname2", "email@email.com"), 1622549341)
                )
            )
        }

        mMessageRecyclerView?.layoutManager = LinearLayoutManager(activity)
        mMessageRecyclerView?.adapter = mMessageAdapter

        return root
    }
}
