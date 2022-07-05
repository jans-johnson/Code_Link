package com.jns.codelink.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.jns.codelink.R


class ProfileDetailsFragment : Fragment() {

    lateinit var etDetName:EditText
    lateinit var etDetDesc:EditText
    lateinit var etDetLocation:EditText
    lateinit var etDetLinks:EditText
    lateinit var etDetSkills:EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_profile_details, container, false)
        etDetName=view.findViewById(R.id.etDetName)
        etDetDesc=view.findViewById(R.id.etDetDesc)
        etDetLocation=view.findViewById(R.id.etDetLocation)
        etDetLinks=view.findViewById(R.id.etDetLinks)
        etDetSkills=view.findViewById(R.id.etDetSkills)

        return view
    }

    public fun getDetails():MutableMap<String,Any>
    {

        val details: MutableMap<String, Any> = HashMap()
        details["name"] = etDetName.text.toString()
        details["description"]=etDetDesc.text.toString()
        details["location"]=etDetLocation.text.toString()
        details["links"]=etDetLinks.text.toString()
        details["skills"]=etDetSkills.text.toString()
        return details
    }
}