package com.jns.codelink.adapters

import android.app.Person
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jns.codelink.R
import com.jns.codelink.models.Chat
import com.jns.codelink.models.Project

class AddMemberAdapter(private val orderList: ArrayList<Chat>) :
    RecyclerView.Adapter<AddMemberAdapter.ViewHolder>() {

    lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_member_single_row, parent, false)

        return ViewHolder(view,mListener)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(
        holder: AddMemberAdapter.ViewHolder,
        position: Int
    ) {
        holder.tvMemberName.text = orderList[position].person

        //Code for on click Listener to be defined here
    }

    class ViewHolder(view: View, listener:onItemClickListener) : RecyclerView.ViewHolder(view) {
        val tvMemberName: TextView = view.findViewById(R.id.tvMemberName)
        val ivPlus: ImageView=view.findViewById((R.id.ivPlus))
        init{
            view.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
            ivPlus.setOnClickListener{
                Toast.makeText(view.context, "Plus Button Clicked", Toast.LENGTH_SHORT).show()
        }


        }
    }


}