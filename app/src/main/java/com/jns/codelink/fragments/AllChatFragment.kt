package com.jns.codelink.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.*
import com.jns.codelink.R
import com.jns.codelink.adapters.*
import com.jns.codelink.models.Chat
import com.jns.codelink.models.Project

class AllChatFragment : Fragment() {

    private lateinit var rvAllChatList: RecyclerView
    private lateinit var allChatsList: ArrayList<Chat> //required?
    private lateinit var projectAdapter: AllChatAdapter
    //lateinit var layoutManager: RecyclerView.LayoutManager
    //private lateinit var mAuth: FirebaseAuth
    //private lateinit var mDbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        allChatsList = ArrayList()
        projectAdapter = AllChatAdapter(activity as Context,allChatsList)
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_allchat, container, false)
        //layoutManager = LinearLayoutManager(activity)

        rvAllChatList= view.findViewById(R.id.rvAllChatList)

        rvAllChatList.layoutManager  = LinearLayoutManager(activity)
        rvAllChatList.adapter = projectAdapter

        //this is to display all users logged in. Has to be changed and made to those swiped right
        /*mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()*/

        allChatsList.add(Chat("Jans","Hello!","4:13","5","1QYxE3yfqaULZEmY8EC41H7V4913"))
        allChatsList.add(Chat("Jans","Hello! hey my name is jishaaaaaa whats your name","4:13","5","1QYxE3yfqaULZEmY8EC41H7V4913"))
        allChatsList.add(Chat("Jans","Hello!","4:13","5","1QYxE3yfqaULZEmY8EC41H7V4913"))

        //val projectAdapter = AllChatAdapter(activity as Context, allChatsList)
        //rvAllChatList.adapter = projectAdapter
        //rvAllChatList.layoutManager = layoutManager

        //this is to display all users logged in. Has to be changed and made to those swiped right
        /*mDbRef.child("users").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                allChatsList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(Chat::class.java) //might cause error
                    allChatsList.add(currentUser!!)
                }
                projectAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })*/

        return view
    }



}