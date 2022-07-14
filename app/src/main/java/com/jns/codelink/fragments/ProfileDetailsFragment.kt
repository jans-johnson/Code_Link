package com.jns.codelink.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.jns.codelink.R


class ProfileDetailsFragment : Fragment() {

    lateinit var etDetName:EditText
    lateinit var etDetDesc:EditText
    lateinit var etDetLocation:EditText
    lateinit var etDetSkills:EditText
    lateinit var lvLinks:ListView
    lateinit var ivAddLink:ImageView
    lateinit var linksList:ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_profile_details, container, false)
        etDetName=view.findViewById(R.id.etDetName)
        etDetDesc=view.findViewById(R.id.etDetDesc)
        etDetLocation=view.findViewById(R.id.etDetLocation)
        etDetSkills=view.findViewById(R.id.etDetSkills)

        lvLinks=view.findViewById(R.id.lvDetLinks)
        ivAddLink=view.findViewById(R.id.ivDetAddLink)

        linksList= ArrayList()

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireActivity(), R.layout.links_single_row,R.id.tvLinkStyle, linksList)
        lvLinks.adapter=adapter

        ivAddLink.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
            builder.setTitle("Enter the link")

            val customLayout: View = layoutInflater
                .inflate(
                    R.layout.dialog_get_link,
                    null
                )
            builder.setView(customLayout)
            builder.setPositiveButton(
                    "Add"
                ) { dialog, which ->
                val editText = customLayout.findViewById<EditText>(R.id.etDialogLink)
                    linksList.add(editText.text.toString())
                    adapter.notifyDataSetChanged()
                }
            val dialog = builder.create()
            dialog.show()
        }

        return view
    }

    public fun getDetails():MutableMap<String,Any>
    {

        val details: MutableMap<String, Any> = HashMap()
        details["name"] = etDetName.text.toString()
        details["description"]=etDetDesc.text.toString()
        details["location"]=etDetLocation.text.toString()
        details["links"]=linksList.toString()
        details["skills"]=etDetSkills.text.toString()
        return details
    }
}