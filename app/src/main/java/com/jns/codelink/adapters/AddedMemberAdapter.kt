package com.jns.codelink.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jns.codelink.R
import com.jns.codelink.models.Chat


class AddedMemberAdapter(private val orderList: ArrayList<Chat>) :
    RecyclerView.Adapter<AddedMemberAdapter.ViewHolder>() {

    //lateinit var Listener2: onItemClickListener2

    /*interface onItemClickListener2{
        fun onItemClick2(position: Int)
        fun onMinusClick2(position:Int)
    }

    fun setOnItemClickListener(listener: AddedMemberAdapter.onItemClickListener2){
        Listener2=listener
    }*/

    class ViewHolder(view: View /*,listener: onItemClickListener2*/) : RecyclerView.ViewHolder(view) {
        val tvMemberName: TextView = view.findViewById(R.id.tvMemberName)
        /*val ivPlus: ImageView =view.findViewById((R.id.ivPlus))
        init{
            view.setOnClickListener{
                listener.onItemClick2(adapterPosition)
            }
            ivPlus.setOnClickListener{
                listener.onMinusClick2(adapterPosition)
            }

        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_member_single_row, parent, false)

        return ViewHolder(view/*,Listener2*/)
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