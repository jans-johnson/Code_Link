package com.jns.codelink.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.jns.codelink.R
import com.jns.codelink.activities.ChatActivity
import com.jns.codelink.activities.LoginActivity
import com.jns.codelink.models.User
import kotlin.collections.ArrayList

class ProfileFragment(var fromExternal:Int, var data: User?) : Fragment() {


    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    lateinit var tvProfileName:TextView
    lateinit var tvProfileDescription:TextView
    lateinit var tvProfileEmail:TextView
    lateinit var tvProfileSkills:TextView
    lateinit var tvProfileAddress:TextView
    lateinit var ivLogout:ImageView
    lateinit var profileProgressLayout:RelativeLayout
    lateinit var lvProfileLinks:ListView
    lateinit var btnProfileMessage: Button
    lateinit var list:ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile, container, false)

        tvProfileName=view.findViewById(R.id.tvProfileName)
        tvProfileDescription=view.findViewById(R.id.tvProfileDescription)
        tvProfileEmail=view.findViewById(R.id.tvProfileEmail)
        tvProfileSkills=view.findViewById(R.id.tvProfileSkills)
        tvProfileAddress=view.findViewById(R.id.tvProfileAddress)
        profileProgressLayout=view.findViewById(R.id.profileProgressLayout)
        lvProfileLinks=view.findViewById(R.id.lvProfileLinks)
        btnProfileMessage=view.findViewById(R.id.btnProfileMessage)
        ivLogout=view.findViewById(R.id.ivLogout)

        database = FirebaseDatabase.getInstance().reference
        auth = Firebase.auth

        ivLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity,LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        btnProfileMessage.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name",data!!.name)
            intent.putExtra("uid",data!!.uid)
            requireContext().startActivity(intent)
        }

        if(fromExternal==1)
        {
            ivLogout.visibility=View.GONE
            tvProfileName.text=data!!.name
            tvProfileDescription.text=data!!.description
            tvProfileEmail.text=data!!.email
            tvProfileSkills.text=data!!.skills
            tvProfileAddress.text=data!!.location

            val str=data!!.links.drop(1).dropLast(1)
            list=ArrayList(str.split(","))
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireActivity(), R.layout.links_single_row,R.id.tvLinkStyle, list)
            lvProfileLinks.adapter=adapter

        }
        else
        {
            btnProfileMessage.visibility=View.GONE

            profileProgressLayout.visibility=View.VISIBLE

            val userId= auth.currentUser!!.uid
            database.child("users").child(userId).get().addOnSuccessListener {

                val details = it.getValue(User::class.java) as User
                tvProfileName.text=details.name
                tvProfileDescription.text=details.description
                tvProfileEmail.text=details.email
                tvProfileSkills.text=details.skills
                tvProfileAddress.text=details.location


                val str=details.links.drop(1).dropLast(1)
                list=ArrayList(str.split(","))
                val adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireActivity(), R.layout.links_single_row,R.id.tvLinkStyle, list)
                lvProfileLinks.adapter=adapter

                profileProgressLayout.visibility=View.GONE

            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }
        }



        return view
    }

}