package com.jns.codelink.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jns.codelink.R
import com.jns.codelink.adapters.CardAdapter
import com.jns.codelink.models.Chat
import com.jns.codelink.models.Notification
import com.jns.codelink.models.Project
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import com.lorentzos.flingswipe.SwipeFlingAdapterView.onFlingListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class SwipeFragment : Fragment() {

    lateinit var ivSwipeRight:ImageView
    lateinit var ivSwipeLeft:ImageView
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_swipe, container, false)


        database = FirebaseDatabase.getInstance().reference
        auth = Firebase.auth
        val userId= auth.currentUser!!.uid
        val db = Firebase.firestore

        //temporary list used to display output, will be replaced later
        val list=ArrayList<Project>()

        val arrayAdapter= this.activity?.let { CardAdapter(it,R.layout.swipecard_design,list) }

        db.collection("projects").get().addOnSuccessListener {
            it.documents.forEach {
                val data=it.data
                if(!data?.get("owner").toString().equals(userId))
                {
                    database.child("project_swipes").child(data?.getValue("id").toString()).get().addOnSuccessListener {
                        if(!it.value.toString().contains(userId)) {
                            list.add(
                                Project(
                                    data?.getValue("id").toString().toInt(),
                                    data?.getValue("heading").toString(),
                                    data?.getValue("description").toString(),
                                    data?.getValue("language").toString(),
                                    data?.getValue("field").toString(),
                                    data?.getValue("difficulty").toString(),
                                    data?.getValue("type").toString(),
                                    data?.getValue("owner").toString()
                                )
                            )

                        }
                        arrayAdapter?.notifyDataSetChanged()
                    }
                }
            }
        }
        val swipeFlingAdapterView=view.findViewById<SwipeFlingAdapterView>(R.id.cvAdapter)
        ivSwipeRight=view.findViewById(R.id.ivSwipeRight)
        ivSwipeLeft=view.findViewById(R.id.ivSwipeLeft)


        swipeFlingAdapterView.adapter=arrayAdapter
        swipeFlingAdapterView.setFlingListener(object : onFlingListener {
            override fun removeFirstObjectInAdapter() {
                list.removeAt(0)
                arrayAdapter!!.notifyDataSetChanged()
            }

            override fun onLeftCardExit(o: Any) {
                val item=o as Project
                database.child("project_swipes").child(item.id.toString()).child("left").push().setValue(userId)
            }
            override fun onRightCardExit(o: Any) {
                val item=o as Project
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("HH:mm")
                val formatted = current.format(formatter)
                database.child("project_swipes").child(item.id.toString()).child("right").push().setValue(userId)

                database.child("users").child(auth.currentUser!!.uid.toString()).get().addOnSuccessListener {
                    database.child("notifications").child(item.owner).push().setValue(
                        Notification(
                            it.child("username").value.toString(),
                            "Swiped Right",
                            formatted,
                            o.heading
                        )
                    )
                }

            }
            override fun onAdapterAboutToEmpty(i: Int) {}
            override fun onScroll(v: Float) {}
        })


        return view
    }

}