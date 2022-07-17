package com.jns.codelink.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.jns.codelink.R
import com.jns.codelink.adapters.GroupChatAdapter
import com.jns.codelink.models.Chat
import com.jns.codelink.models.Project

class OngoingListFragment : Fragment() {

    lateinit var rvOngoingList:RecyclerView
    var ongoingProjectsList = arrayListOf<Chat>()
    lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var mDbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_ongoing_list, container, false)
        layoutManager = LinearLayoutManager(activity)

        rvOngoingList=view.findViewById(R.id.rvOngoingList)


        ongoingProjectsList.clear()
        val projectAdapter = GroupChatAdapter(activity as Context, ongoingProjectsList)
        rvOngoingList.adapter = projectAdapter
        rvOngoingList.layoutManager = layoutManager
        val mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()


        mDbRef.child("GroupChat").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                for(postSnapshot in snapshot.children){
                    if(postSnapshot.toString().contains(mAuth.uid.toString()))
                    {
                        var groupId=""
                        var groupName=""
                        postSnapshot.children.forEach {
                            if(it.key.toString().equals("groupId"))
                                groupId=it.value.toString()
                            else if(it.key.toString().equals("groupName"))
                                groupName=it.value.toString()
                        }
                        val chat= Chat(groupName, uid = groupId)
                        ongoingProjectsList.add(chat)
                        projectAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        return view
    }

}