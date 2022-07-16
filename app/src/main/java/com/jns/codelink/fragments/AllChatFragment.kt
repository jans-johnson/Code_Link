package com.jns.codelink.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.jns.codelink.models.User

class AllChatFragment : Fragment() {

    private lateinit var rvAllChatList: RecyclerView
    private lateinit var allChatsList: ArrayList<Chat>
    private lateinit var projectAdapter: AllChatAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        allChatsList = ArrayList()
        projectAdapter = AllChatAdapter(activity as Context,allChatsList)
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_allchat, container, false)
        layoutManager = LinearLayoutManager(activity)

        rvAllChatList= view.findViewById(R.id.rvAllChatList)

        rvAllChatList.layoutManager  = LinearLayoutManager(activity)
        rvAllChatList.adapter = projectAdapter

        //this is to display all users logged in. Has to be changed and made to those swiped right
        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()


        val projectAdapter = AllChatAdapter(activity as Context, allChatsList)
        rvAllChatList.adapter = projectAdapter
        rvAllChatList.layoutManager = layoutManager

        mDbRef.child("chats").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                allChatsList.clear()

                for(postSnapshot in snapshot.children){
                    if (postSnapshot.key.toString().startsWith(mAuth.currentUser!!.uid))
                    {
                        val uid=postSnapshot.key.toString().replace(mAuth.currentUser!!.uid,"")
                        mDbRef.child("users").child(uid).get().addOnCompleteListener {
                            val added=it.result.getValue(Chat::class.java) as Chat
                            if(!allChatsList.contains(added))
                            allChatsList.add(added)
                            projectAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        return view
    }



}