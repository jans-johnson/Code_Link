package com.jns.codelink.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jns.codelink.R
import com.jns.codelink.adapters.AllChatAdapter
import com.jns.codelink.adapters.GroupChatAdapter
import com.jns.codelink.models.Chat

class GroupChatFragment : Fragment() {

    lateinit var rvGroupChatList: RecyclerView
    var groupChatList = arrayListOf<Chat>() //required?
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_groupchat, container, false)
        layoutManager = LinearLayoutManager(activity)

        rvGroupChatList=view.findViewById(R.id.rvGroupChatList)

        Log.d("jans","called")
        groupChatList.add(Chat("Maris","Hello!","4:13","5"))
        groupChatList.add(Chat("Maris","Hello!","4:13","5"))
        groupChatList.add(Chat("Maris","Hello!","4:13","5"))

        val projectAdapter = GroupChatAdapter(activity as Context, groupChatList)
        rvGroupChatList.adapter = projectAdapter
        rvGroupChatList.layoutManager = layoutManager
        return view
    }

}