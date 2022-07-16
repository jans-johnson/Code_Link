package com.jns.codelink.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.jns.codelink.R
import com.jns.codelink.fragments.ProfileDetailsFragment
import com.jns.codelink.fragments.SignupFragment
import com.jns.codelink.models.User

class RegisterActivity : AppCompatActivity() {

    lateinit var ivBack:ImageView
    lateinit var btnRegister:Button
    lateinit var registerProgressLayout:RelativeLayout
    private lateinit var auth: FirebaseAuth;
    lateinit var details: MutableMap<String, Any>
    lateinit var details2: MutableMap<String, Any>
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ivBack=findViewById(R.id.ivBack)
        btnRegister=findViewById(R.id.btnRegister)
        registerProgressLayout=findViewById(R.id.registerProgressLayout)
        auth = Firebase.auth

        database = FirebaseDatabase.getInstance().reference

        supportFragmentManager.beginTransaction()
            .replace(R.id.flRegister, SignupFragment(), "SignupFragment").commit()

        ivBack.setOnClickListener {
            Toast.makeText(this,"Signup Incomplete !!",Toast.LENGTH_SHORT).show()
            finish()
        }
        btnRegister.setOnClickListener {

            if (btnRegister.text=="Register") {
                val frag: SignupFragment? =
                    supportFragmentManager.findFragmentByTag("SignupFragment") as SignupFragment?

                if(frag!!.validate()) {
                    details = frag!!.getDetails()

                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.flRegister,
                            ProfileDetailsFragment(),
                            "ProfileDetailsFragment"
                        )
                        .commit()
                    btnRegister.text = "Confirm"
                }
            }
            else
            {
                registerProgressLayout.visibility= View.VISIBLE
                val frag: ProfileDetailsFragment? =
                    supportFragmentManager.findFragmentByTag("ProfileDetailsFragment") as ProfileDetailsFragment?

                auth.createUserWithEmailAndPassword(details["email"].toString(), details["password"].toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            val userId= auth.currentUser!!.uid

                            details2=frag!!.getDetails()
                            val newUser=User(details2["name"].toString(),
                            details["username"].toString(),
                            details["email"].toString(),
                            details2["description"].toString(),
                            details2["location"].toString(),
                            details2["links"].toString(),
                            details2["skills"].toString(),
                            userId)

                            database.child("users").child(userId).setValue(newUser)
                            registerProgressLayout.visibility=View.GONE
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("jans", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }

            }
        }


    }

}