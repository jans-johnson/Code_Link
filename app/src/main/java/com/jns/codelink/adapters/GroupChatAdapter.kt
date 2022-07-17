package com.jns.codelink.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jns.codelink.R
import com.jns.codelink.activities.ChatActivity
import com.jns.codelink.models.Chat

class GroupChatAdapter(val context: Context, private val orderList: ArrayList<Chat>) :
    RecyclerView.Adapter<GroupChatAdapter.ViewHolderProject>() {

    class ViewHolderProject(view: View) : RecyclerView.ViewHolder(view) {
        val tvChatPerson: TextView = view.findViewById(R.id.tvChatPerson)
        val tvChatDescription: TextView = view.findViewById(R.id.tvChatDescription)
        val tvChatNotif: TextView = view.findViewById(R.id.tvChatNotif)
        val tvChatTime: TextView = view.findViewById(R.id.tvChatTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProject {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_single_row, parent, false)

        return ViewHolderProject(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: ViewHolderProject, position: Int) {

        holder.tvChatPerson.text =
            orderList[position].name//should this be the variable created or the text id
        holder.tvChatDescription.text = orderList[position].description
        holder.tvChatNotif.text = orderList[position].notif_num
        holder.tvChatTime.text = orderList[position].time

        //Code for on click Listener to be defined here
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("isGroup", 1)
            intent.putExtra("id", orderList[position].uid)
            context.startActivity(intent)
        }
    }
}