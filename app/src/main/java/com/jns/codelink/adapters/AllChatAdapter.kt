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

class AllChatAdapter(val context: Context, val orderList: ArrayList<Chat>):
    RecyclerView.Adapter<AllChatAdapter.ViewHolderProject>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProject {
        val view: View = LayoutInflater.from(context).inflate(R.layout.chat_single_row, parent, false)

        return ViewHolderProject(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: ViewHolderProject, position: Int) {

        val currentUser = orderList[position]
        holder.tvChatPerson.text = currentUser.name
        holder.tvChatDescription.text = currentUser.description
        holder.tvChatNotif.text = currentUser.notif_num
        holder.tvChatTime.text = currentUser.time

        //Code for on click Listener to be defined here
        holder.itemView.setOnClickListener {
            val intent = Intent(context,ChatActivity::class.java)
            intent.putExtra("name",currentUser.name)
            intent.putExtra("uid",currentUser.uid)
            context.startActivity(intent)

        }
    }

    class ViewHolderProject(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvChatPerson: TextView = itemView.findViewById(R.id.tvChatPerson)
        val tvChatDescription: TextView = itemView.findViewById(R.id.tvChatDescription)
        val tvChatNotif: TextView = itemView.findViewById(R.id.tvChatNotif)
        val tvChatTime: TextView = itemView.findViewById(R.id.tvChatTime)
    }
}