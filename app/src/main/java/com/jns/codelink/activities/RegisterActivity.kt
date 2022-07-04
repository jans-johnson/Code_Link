package com.jns.codelink.activities

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jns.codelink.R
import com.jns.codelink.fragments.ProfileDetailsFragment
import com.jns.codelink.fragments.SignupFragment

class RegisterActivity : AppCompatActivity() {

    lateinit var ivBack:ImageView
    lateinit var btnRegister:Button
    private lateinit var auth: FirebaseAuth;
    lateinit var details: MutableMap<String, Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ivBack=findViewById(R.id.ivBack)
        btnRegister=findViewById(R.id.btnRegister)
        auth = Firebase.auth

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

                details= frag!!.getDetails()

                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.flRegister,
                        ProfileDetailsFragment(),
                        "ProfileDetailsFragment"
                    )
                    .commit()
                btnRegister.text = "Confirm"
            }
            else
            {
                val frag: ProfileDetailsFragment? =
                    supportFragmentManager.findFragmentByTag("ProfileDetailsFragment") as ProfileDetailsFragment?

                auth.createUserWithEmailAndPassword(details["email"].toString(), details["password"].toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("jans", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }


    }

}