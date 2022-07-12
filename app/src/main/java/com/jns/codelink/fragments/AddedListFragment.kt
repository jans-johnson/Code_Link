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
import com.jns.codelink.adapters.AddedProjectsAdapter
import com.jns.codelink.adapters.AddedProjectsListAdapter
import com.jns.codelink.models.Project

class AddedListFragment : Fragment() {

    lateinit var rvAddedList:RecyclerView
    var addedProjectsList = arrayListOf<Project>()
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_added_list, container, false)
        layoutManager = LinearLayoutManager(activity)

        rvAddedList=view.findViewById(R.id.rvAddedList)

        addedProjectsList.add(Project(1,"Website","web development","python django","Simple web application"))
        addedProjectsList.add(Project(2,"Mobile Application","App development","Android Studio, Kotlin","Simple mobile application"))
        addedProjectsList.add(Project(3,"Shopping Website","web development","HTML, CSS, Javascript","Simple Shopping Applicaiton where you can add items to the cart, carry out payment and checkout"))

        val projectAdapter = AddedProjectsListAdapter(activity as Context, addedProjectsList)
        rvAddedList.adapter = projectAdapter
        rvAddedList.layoutManager = layoutManager
        return view
    }

}