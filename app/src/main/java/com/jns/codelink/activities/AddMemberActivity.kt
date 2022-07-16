package com.jns.codelink.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jns.codelink.R
import com.jns.codelink.adapters.AddMemberAdapter
import com.jns.codelink.adapters.AddedMemberAdapter
import com.jns.codelink.adapters.GroupChatAdapter
import com.jns.codelink.models.Chat
import com.jns.codelink.models.Project
import com.jns.codelink.models.User

class AddMemberActivity:AppCompatActivity(){
    lateinit var rvPotMember: RecyclerView
    lateinit var rvAddedMember: RecyclerView
    var potmembersList = arrayListOf<Chat>()
    var addedmembersList = arrayListOf<Chat>()
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var layoutManager2: RecyclerView.LayoutManager

    //var projectAdapter:RecyclerView.Adapter<AddMemberAdapter.ViewHolder>?=null
    var projectAdapter2:RecyclerView.Adapter<AddedMemberAdapter.ViewHolder>?=null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)
        layoutManager = LinearLayoutManager(this) //should i pass anything else
        layoutManager2 = LinearLayoutManager(this)

        //ask abt needing view?
        rvPotMember=findViewById(R.id.rvPotMembers)
        rvAddedMember=findViewById(R.id.rvAddedMembers)

        potmembersList.add(Chat("Maris","Hello!","4:13","5"))
        potmembersList.add(Chat("Megha","Hello!","4:13","5"))
        potmembersList.add(Chat("Joanne","Hello!","4:13","5"))

        addedmembersList.add(Chat("Maris","Hello!","4:13","5"))
        addedmembersList.add(Chat("Megha","Hello!","4:13","5"))

        var projectAdapter = AddMemberAdapter(potmembersList)
        rvPotMember.adapter = projectAdapter
        rvPotMember.layoutManager = layoutManager
        projectAdapter.setOnItemClickListener(object: AddMemberAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@AddMemberActivity,"you clicked on item $position",Toast.LENGTH_SHORT).show()
            }

        })


        //can we do this?
        projectAdapter2 = AddedMemberAdapter(addedmembersList)
        rvAddedMember.adapter = projectAdapter2
        rvAddedMember.layoutManager = layoutManager2


    }
}