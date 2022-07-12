package com.jns.codelink.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jns.codelink.R
import com.jns.codelink.adapters.AddedProjectsListAdapter
import com.jns.codelink.adapters.NotificationAdapter
import com.jns.codelink.models.Notification
import com.jns.codelink.models.Project

class NotificationFragment : Fragment() {

    lateinit var rvNotification: RecyclerView
    var notificationList = arrayListOf<Notification>()
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_notification, container, false)
        layoutManager = LinearLayoutManager(activity)

        rvNotification=view.findViewById(R.id.rvNotification)

        notificationList.add(Notification("Jisha","swiped right","10:30","python django"))
        notificationList.add(Notification("Jisha","swiped right","10:30","python django"))
        notificationList.add(Notification("Jisha","swiped right","10:30","python django"))

        val projectAdapter = NotificationAdapter(activity as Context, notificationList)
        rvNotification.adapter = projectAdapter
        rvNotification.layoutManager = layoutManager
        return view
    }

}