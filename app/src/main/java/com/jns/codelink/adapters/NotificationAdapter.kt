package com.jns.codelink.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jns.codelink.R
import com.jns.codelink.models.Notification
import com.jns.codelink.models.Project


class NotificationAdapter(val context: Context, private val orderList: ArrayList<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolderProject>() {

    class ViewHolderProject(view: View) : RecyclerView.ViewHolder(view) {
        val tvNotifDescription: TextView = view.findViewById(R.id.tvNotifDescription)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProject {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_single_row, parent, false)

        return ViewHolderProject(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: ViewHolderProject, position: Int) {

        holder.tvNotifDescription.text = orderList[position].person //append description and project name
        holder.tvTime.text = orderList[position].time
        //Code for on click Listener to be defined here
    }
}