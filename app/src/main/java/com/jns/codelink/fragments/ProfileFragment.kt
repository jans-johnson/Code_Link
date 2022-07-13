package com.jns.codelink.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.jns.codelink.R
import com.jns.codelink.models.User

class ProfileFragment : Fragment() {


    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth;
    lateinit var tvProfileName:TextView
    lateinit var tvProfileDescription:TextView
    lateinit var tvProfileEmail:TextView
    lateinit var tvProfileSkills:TextView

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

        database = FirebaseDatabase.getInstance().reference
        auth = Firebase.auth

        val userId= auth.currentUser!!.uid
        database.child("users").child(userId).get().addOnSuccessListener {

            val details = it.getValue(User::class.java) as User
            tvProfileName.text=details.name
            tvProfileDescription.text=details.description
            tvProfileEmail.text=details.email
            tvProfileSkills.text=details.skills
            
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }


        return view
    }

}