package com.jns.codelink.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
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

    lateinit var rgAddDifficulty:RadioGroup
    lateinit var rgAddType:RadioGroup

    lateinit var rbAdvanced:RadioButton
    lateinit var rbIntermediate:RadioButton
    lateinit var rbBeginner:RadioButton

    lateinit var rbProfessional:RadioButton
    lateinit var rbExplorative:RadioButton

    lateinit var ivAddBack:ImageView


    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_proj)

        rgAddDifficulty=findViewById(R.id.rgAddDifficulty)
        rgAddType=findViewById(R.id.rgAddType)

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

        ivAddBack=findViewById(R.id.ivAddBack)

        database = FirebaseDatabase.getInstance().reference
        auth = Firebase.auth

        ivAddBack.setOnClickListener {

            Toast.makeText(this,"Project Not Added",Toast.LENGTH_SHORT).show()
            finish()
        }

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

                    database.child("projects").child(totalProjects.toString()).setValue(project).addOnCompleteListener {
                        database.child("totalProjects").setValue(totalProjects.toString()).addOnCompleteListener {
                            Toast.makeText(this,"Project Added Successfully",Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }

                }.addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }
            }
        }

    }
    fun validate():Boolean
    {
        if(etAddProjectName.text.isEmpty())
        {
            etAddProjectName.error="Enter Project Name"
            return false
        }
        else if(etAddDescription.text.isEmpty())
        {
            etAddDescription.error="Enter Project Description"
            return false
        }
        else if(etAddLanguage.text.isEmpty())
        {
            etAddLanguage.error="Enter Languages working on"
            return false
        }
        else if(etAddField.text.isEmpty()) {
            etAddField.error = "Enter Field"
            return false
        }
        else if (rgAddDifficulty.checkedRadioButtonId == -1)
        {
            Toast.makeText(this,"Choose Difficulty before Continuing",Toast.LENGTH_SHORT).show()
            return false
        }
        else if(rgAddType.checkedRadioButtonId==-1)
        {
            Toast.makeText(this,"Choose Type",Toast.LENGTH_SHORT).show()
            return false
        }
        else
            return true
    }
}