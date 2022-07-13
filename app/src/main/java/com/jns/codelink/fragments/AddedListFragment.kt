package com.jns.codelink.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jns.codelink.R
import com.jns.codelink.adapters.AddedProjectsAdapter
import com.jns.codelink.adapters.AddedProjectsListAdapter
import com.jns.codelink.models.Project
import com.jns.codelink.models.User

class AddedListFragment : Fragment() {

    lateinit var rvAddedList:RecyclerView
    var addedProjectsList = arrayListOf<Project>()
    lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_added_list, container, false)
        layoutManager = LinearLayoutManager(activity)

        rvAddedList=view.findViewById(R.id.rvAddedList)

        database = FirebaseDatabase.getInstance().reference
        auth = Firebase.auth
        val userId= auth.currentUser!!.uid
        val db = Firebase.firestore


        addedProjectsList.clear()

        db.collection("projects").whereEqualTo("owner",userId).get().addOnCompleteListener {
            it.result.documents.forEach {

                addedProjectsList.add(Project(it.data?.get("id").toString().toInt(),it.data?.get("heading").toString(),
                    it.data?.get("description").toString()))



            }
            val projectAdapter = AddedProjectsListAdapter(activity as Context, addedProjectsList)
            rvAddedList.adapter = projectAdapter
            rvAddedList.layoutManager = layoutManager
        }

        return view
    }

}