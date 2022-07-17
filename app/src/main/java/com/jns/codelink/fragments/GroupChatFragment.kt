package com.jns.codelink.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.jns.codelink.R
import com.jns.codelink.adapters.GroupChatAdapter
import com.jns.codelink.models.Chat
import com.jns.codelink.models.GroupChat

class GroupChatFragment : Fragment() {

    lateinit var rvGroupChatList: RecyclerView
    var groupChatList = arrayListOf<Chat>() //required?
    lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var mDbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_groupchat, container, false)
        layoutManager = LinearLayoutManager(activity)

        rvGroupChatList=view.findViewById(R.id.rvGroupChatList)

        val projectAdapter = GroupChatAdapter(activity as Context, groupChatList)
        rvGroupChatList.adapter = projectAdapter
        rvGroupChatList.layoutManager = layoutManager
        mDbRef = FirebaseDatabase.getInstance().getReference()
        val mAuth = FirebaseAuth.getInstance()

        mDbRef.child("GroupChat").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                groupChatList.clear()

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
                        val chat=Chat(groupName,uid=groupId)
                        groupChatList.add(chat)
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