package com.jns.codelink.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.jns.codelink.R
import com.jns.codelink.adapters.CardAdapter
import com.jns.codelink.models.Project
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import com.lorentzos.flingswipe.SwipeFlingAdapterView.onFlingListener


class SwipeFragment : Fragment() {

    lateinit var ivSwipeRight:ImageView
    lateinit var ivSwipeLeft:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_swipe, container, false)

        //temporary list used to display output, will be replaced later
        val list=ArrayList<Project>()
        list.add(Project(1,"Website","Simple web application","Python","Web Development","advanced","professional"))
        list.add(Project(2,"Application","Simple Android application","Kotlin","Android Development","advanced","professional"))
        list.add(Project(3,"Android Development","Simple web application","Python","Web Development","advanced","professional"))

        val swipeFlingAdapterView=view.findViewById<SwipeFlingAdapterView>(R.id.cvAdapter)
        ivSwipeRight=view.findViewById(R.id.ivSwipeRight)
        ivSwipeLeft=view.findViewById(R.id.ivSwipeLeft)


        var arrayAdapter=
            this.activity?.let { CardAdapter(it,R.layout.swipecard_design,list) }

        swipeFlingAdapterView.adapter=arrayAdapter
        swipeFlingAdapterView.setFlingListener(object : onFlingListener {
            override fun removeFirstObjectInAdapter() {
                list.removeAt(0)
                arrayAdapter!!.notifyDataSetChanged()
            }

            override fun onLeftCardExit(o: Any) {

                Log.d("jans","Left called")
            }
            override fun onRightCardExit(o: Any) {
                Log.d("jans","Right called")
            }
            override fun onAdapterAboutToEmpty(i: Int) {}
            override fun onScroll(v: Float) {}
        })


        return view
    }

}