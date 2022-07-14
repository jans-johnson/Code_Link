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
import com.jns.codelink.adapters.*
import com.jns.codelink.models.Chat
import com.jns.codelink.models.Project

class AllChatFragment : Fragment() {

    lateinit var rvAllChatList:RecyclerView
    var allChatsList = arrayListOf<Chat>() //required?
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_allchat, container, false)
        layoutManager = LinearLayoutManager(activity)

        rvAllChatList=view.findViewById(R.id.rvAllChatList)

        allChatsList.add(Chat("Jans","Hello!","4:13","5"))
        allChatsList.add(Chat("Jans","Hello! hey my name is jishaaaaaa whats your name","4:13","5"))
        allChatsList.add(Chat("Jans","Hello!","4:13","5"))

        val projectAdapter = AllChatAdapter(activity as Context, allChatsList)
        rvAllChatList.adapter = projectAdapter
        rvAllChatList.layoutManager = layoutManager
        return view
    }

}