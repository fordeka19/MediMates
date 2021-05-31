package com.medimates.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.medimates.R
import com.medimates.viewModel.home.HomeViewModel

class HomeAdapter(private val groups: ArrayList<String>, private val viewModel: HomeViewModel) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.group_row_item, viewGroup, false)

        return ViewHolder(view, viewModel)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.groupName.text = groups[position]
    }

    override fun getItemCount() = groups.size

    class ViewHolder(view: View, viewModel: HomeViewModel) : RecyclerView.ViewHolder(view) {
        val groupName: TextView = view.findViewById(R.id.group_name)

        init {
            groupName.setOnClickListener { viewModel.navigateToGroupMessagingFragment(groupName.text.toString()) }
        }
    }
}
