package com.jns.codelink.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.jns.codelink.R
import com.jns.codelink.models.Project

class AddProjActivity : AppCompatActivity() {


    private lateinit var database: DatabaseReference
    lateinit var etAddProjectName:EditText
    lateinit var etAddDescription:EditText
    lateinit var etAddLanguage:EditText
    lateinit var etAddField:EditText
    lateinit var btnAdd:Button

    lateinit var rbAdvanced:RadioButton
    lateinit var rbIntermediate:RadioButton
    lateinit var rbBeginner:RadioButton

    lateinit var rbProfessional:RadioButton
    lateinit var rbExplorative:RadioButton


    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_proj)

        rbAdvanced=findViewById(R.id.rbAdvanced)
        rbIntermediate=findViewById(R.id.rbIntermediate)
        rbBeginner=findViewById(R.id.rbBeginner)

        rbProfessional=findViewById(R.id.rbProfessional)
        rbExplorative=findViewById(R.id.rbExplorative)


        etAddProjectName=findViewById(R.id.etAddProjectName)
        etAddDescription=findViewById(R.id.etAddDescription)
        etAddLanguage=findViewById(R.id.etAddLanguage)
        etAddField=findViewById(R.id.etAddField)
        btnAdd=findViewById(R.id.btnAdd)

        database = FirebaseDatabase.getInstance().reference
        auth = Firebase.auth

        btnAdd.setOnClickListener {
            if(validate())
            {
                lateinit var difficulty:String
                lateinit var type:String

                if (rbBeginner.isChecked)
                {
                    difficulty="Beginner"
                }
                else if(rbIntermediate.isChecked)
                {
                    difficulty="Intermediate"
                }
                else
                    difficulty="Advanced"


                if(rbProfessional.isChecked)
                    type="Professional"
                else
                    type="Explorative"

                database.child("totalProjects").get().addOnSuccessListener {

                    val userId= auth.currentUser!!.uid

                    var totalProjects=it.value.toString().toInt()
                    totalProjects++
                    val project=Project(totalProjects,etAddProjectName.text.toString(),etAddDescription.text.toString(),etAddLanguage.text.toString()
                    ,etAddField.text.toString(),difficulty,type,userId)

                    database.child("projects").child(totalProjects.toString()).setValue(project)

                    database.child("totalProjects").setValue(totalProjects.toString())

                }.addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }
            }
        }

    }
    fun validate():Boolean
    {
        return true
    }
}