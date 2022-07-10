package com.jns.codelink.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jns.codelink.R
import com.jns.codelink.activities.AddProjActivity
import com.jns.codelink.activities.MainActivity
import com.jns.codelink.adapters.AddedProjectsAdapter


class AddedProjectsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_added_projects, container, false)
        val tlProjects=view.findViewById<TabLayout>(R.id.tlProjects)
        val vpAddedProjects=view.findViewById<ViewPager>(R.id.vpAddedProjects)
        val btnAddProject=view.findViewById<Button>(R.id.btnAddProject)

        btnAddProject.setOnClickListener {
            val intent = Intent(activity, AddProjActivity::class.java)
            startActivity(intent)
        }

        val adapter = activity?.supportFragmentManager?.let { AddedProjectsAdapter(it, tlProjects.tabCount) }
        vpAddedProjects.adapter = adapter

        vpAddedProjects.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlProjects))


        tlProjects.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    vpAddedProjects.currentItem = tab.position
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