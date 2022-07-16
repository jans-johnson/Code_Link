package com.jns.codelink.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.jns.codelink.R
import com.jns.codelink.models.Project

class CardAdapter (context1: Context?,val resourceId: Int,val items:ArrayList<Project>) :
    ArrayAdapter<Project>(context1!!,resourceId,items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var convertView=LayoutInflater.from(parent.context).inflate(R.layout.swipecard_design,parent,false)

        val tvCardHeading:TextView=convertView.findViewById(R.id.tvCardHeading)
        val tvCardType:TextView=convertView.findViewById(R.id.tvCardType)
        val tvCardLanguage:TextView=convertView.findViewById(R.id.tvCardLanguage)
        val tvCardDescription:TextView=convertView.findViewById(R.id.tvCardDescription)

        tvCardHeading.text=items[position].heading
        tvCardType.text=items[position].field
        tvCardLanguage.text=items[position].language
        tvCardDescription.text=items[position].description
        return convertView
    }
}