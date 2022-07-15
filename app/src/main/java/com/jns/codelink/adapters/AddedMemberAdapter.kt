package com.jns.codelink.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jns.codelink.R
import com.jns.codelink.models.Chat


class AddedMemberAdapter(private val orderList: ArrayList<Chat>) :
    RecyclerView.Adapter<AddedMemberAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMemberName: TextView = view.findViewById(R.id.tvMemberName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_member_single_row, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(
        holder: AddedMemberAdapter.ViewHolder,
        position: Int
    ) {
        holder.tvMemberName.text = orderList[position].person

        //Code for on click Listener to be defined here
    }

}