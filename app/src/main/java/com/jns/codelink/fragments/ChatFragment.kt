package com.jns.codelink.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jns.codelink.R
import com.jns.codelink.activities.AddProjActivity
import com.jns.codelink.adapters.AddedProjectsAdapter
import com.jns.codelink.adapters.ChatAdapter

class ChatFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_chat, container, false)
        val tlChats=view.findViewById<TabLayout>(R.id.tlChats)
        val vpChats=view.findViewById<ViewPager>(R.id.vpChats)

        val adapter = childFragmentManager.let { ChatAdapter(it, tlChats.tabCount) }
        vpChats.adapter = adapter


        vpChats.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlChats))


        tlChats.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    vpChats.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


        return view
    }
}