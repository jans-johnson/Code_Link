package com.jns.codelink.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.jns.codelink.R
import com.jns.codelink.adapters.AddMemberAdapter
import com.jns.codelink.adapters.AddedMemberAdapter
import com.jns.codelink.models.User

class AddMemberActivity:AppCompatActivity(){

    lateinit var tvAddMemberHeading: TextView
    lateinit var rvPotMember: RecyclerView
    lateinit var rvAddedMember: RecyclerView
    var potmembersList = arrayListOf<User>()
    var addedmembersList = arrayListOf<User>()
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var layoutManager2: RecyclerView.LayoutManager

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth;

    var projectAdapter:RecyclerView.Adapter<AddMemberAdapter.ViewHolder>?=null
    var projectAdapter2:RecyclerView.Adapter<AddedMemberAdapter.ViewHolder>?=null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)
        layoutManager = LinearLayoutManager(this) //should i pass anything else
        layoutManager2 = LinearLayoutManager(this)

        val heading=intent.getStringExtra("name")
        val id=intent.getStringExtra("id")

        //ask abt needing view?
        rvPotMember=findViewById(R.id.rvPotMembers)
        rvAddedMember=findViewById(R.id.rvAddedMembers)
        tvAddMemberHeading=findViewById(R.id.tvAddMemberHeading)

        tvAddMemberHeading.text=heading

        database = FirebaseDatabase.getInstance().reference
        auth = Firebase.auth
        val userId= auth.currentUser!!.uid

        val projectAdapter = AddMemberAdapter(potmembersList)
        var projectAdapter2 = AddedMemberAdapter(addedmembersList)

        database.child("project_swipes").child(id!!).child("right").addValueEventListener(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    database.child("users").child(it.value.toString()).get().addOnCompleteListener {

                        val obj=it.result.getValue(User::class.java) as User
                        potmembersList.add(obj)
                        projectAdapter?.notifyDataSetChanged()
                    }
                }
                }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        rvPotMember.adapter = projectAdapter
        rvPotMember.layoutManager = layoutManager
        projectAdapter.setOnItemClickListener(object: AddMemberAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@AddMemberActivity,"you clicked on item $position",Toast.LENGTH_SHORT).show()
            }

            override fun onPlusClick(position: Int) {
                addedmembersList.add(potmembersList[position])
                projectAdapter2.notifyItemInserted(addedmembersList.size)
                potmembersList.removeAt(position)
                projectAdapter.notifyItemRemoved(position)
            }

        })


        rvAddedMember.adapter = projectAdapter2
        rvAddedMember.layoutManager = layoutManager2

        projectAdapter2.setOnItemClickListener(object: AddedMemberAdapter.onItemClickListener2{
            override fun onItemClick2(position: Int) {
                Toast.makeText(this@AddMemberActivity,"you clicked on item $position",Toast.LENGTH_SHORT).show()
            }

            override fun onMinusClick2(position: Int) {
                potmembersList.add(addedmembersList[position])
                projectAdapter.notifyItemInserted(potmembersList.size)
                addedmembersList.removeAt(position)
                projectAdapter2.notifyItemRemoved(position)
            }

        })




    }
}
