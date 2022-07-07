package com.jns.codelink.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.jns.codelink.R
import com.jns.codelink.adapters.CardAdapter
import com.jns.codelink.models.SwipeCard
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import com.lorentzos.flingswipe.SwipeFlingAdapterView.onFlingListener


class SwipeFragment : Fragment() {

    lateinit var swipeAdapter: CardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_swipe, container, false)

        //temporary list used to display output, will be replaced later
        val list=ArrayList<SwipeCard>()
        list.add(SwipeCard("Website","web development","python django","Simple web application"))
        list.add(SwipeCard("Mobile Application","App development","Android Studio, Kotlin","Simple mobile application"))
        list.add(SwipeCard("Shopping Website","web development","HTML, CSS, Javascript","Simple Shopping Applicaiton where you can add items to the cart, carry out payment and checkout"))

        var swipeFlingAdapterView=view.findViewById<SwipeFlingAdapterView>(R.id.cvAdapter)

        var arrayAdapter=
            this.activity?.let { CardAdapter(it,R.layout.swipecard_design,list) }

        swipeFlingAdapterView.adapter=arrayAdapter
        swipeFlingAdapterView.setFlingListener(object : onFlingListener {
            override fun removeFirstObjectInAdapter() {
                list.removeAt(0)
                arrayAdapter!!.notifyDataSetChanged()
            }

            override fun onLeftCardExit(o: Any) {}
            override fun onRightCardExit(o: Any) {}
            override fun onAdapterAboutToEmpty(i: Int) {}
            override fun onScroll(v: Float) {}
        })

        return view
    }

}