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
import com.jns.codelink.R
import com.jns.codelink.adapters.AddedProjectsAdapter
import com.jns.codelink.adapters.AddedProjectsListAdapter
import com.jns.codelink.adapters.OngoingProjectsListAdapter
import com.jns.codelink.models.Project

class OngoingListFragment : Fragment() {

    lateinit var rvOngoingList:RecyclerView
    var ongoingProjectsList = arrayListOf<Project>()
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_ongoing_list, container, false)
        layoutManager = LinearLayoutManager(activity)

        rvOngoingList=view.findViewById(R.id.rvOngoingList)


        ongoingProjectsList.clear()
        ongoingProjectsList.add(Project(1,"Website","Simple web application","Python","Web Development","advanced","professional"))
        ongoingProjectsList.add(Project(2,"Application","Simple Android application","Kotlin","Android Development","advanced","professional"))
        ongoingProjectsList.add(Project(3,"Android Development","Simple web application","Python","Web Development","advanced","professional"))

        val projectAdapter = OngoingProjectsListAdapter(activity as Context, ongoingProjectsList)
        rvOngoingList.adapter = projectAdapter
        rvOngoingList.layoutManager = layoutManager
        return view
    }

}