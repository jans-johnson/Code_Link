package com.jns.codelink.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.jns.codelink.R
import com.jns.codelink.adapters.AddedProjectsListAdapter
import com.jns.codelink.adapters.NotificationAdapter
import com.jns.codelink.models.Notification
import com.jns.codelink.models.Project

class NotificationFragment : Fragment() {

    lateinit var rvNotification: RecyclerView
    var notificationList = arrayListOf<Notification>()
    lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_notification, container, false)
        layoutManager = LinearLayoutManager(activity)

        rvNotification=view.findViewById(R.id.rvNotification)

        database = FirebaseDatabase.getInstance().reference
        auth = Firebase.auth
        val userId= auth.currentUser!!.uid

        notificationList.clear()

        val projectAdapter = NotificationAdapter(activity as Context, notificationList)

        database.child("notifications").child(userId).addValueEventListener(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val data=it.getValue(Notification::class.java) as Notification
                    notificationList.add(data)
                    projectAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        rvNotification.adapter = projectAdapter
        rvNotification.layoutManager = layoutManager
        return view
    }

}