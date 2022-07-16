package com.jns.codelink.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jns.codelink.R
import com.jns.codelink.activities.AddMemberActivity
import com.jns.codelink.activities.ChatActivity
import com.jns.codelink.models.Project

class AddedProjectsListAdapter(val context: Context, private val orderList: ArrayList<Project>) :
    RecyclerView.Adapter<AddedProjectsListAdapter.ViewHolderProject>() {

    class ViewHolderProject(view: View) : RecyclerView.ViewHolder(view) {
        val tvProjectHeading: TextView = view.findViewById(R.id.tvProjectHeading)
        val tvProjectDescription: TextView = view.findViewById(R.id.tvNotifDescription)
        val llAddedProject:LinearLayout=view.findViewById(R.id.llAddedProject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProject {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.added_projects_single_row, parent, false)

        return ViewHolderProject(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: ViewHolderProject, position: Int) {

        holder.tvProjectHeading.text = orderList[position].heading
        holder.tvProjectDescription.text = orderList[position].description

        holder.llAddedProject.setOnClickListener {
            val intent = Intent(context, AddMemberActivity::class.java)
            intent.putExtra("name",orderList[position].heading)
            intent.putExtra("id",orderList[position].id.toString())
            context.startActivity(intent)
        }

        //Code for on click Listener to be defined here
    }
}