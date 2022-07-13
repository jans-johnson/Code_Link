package com.jns.codelink.activities

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.jns.codelink.R


class LoginActivity : AppCompatActivity() {

    lateinit var mAuth:FirebaseAuth

    lateinit var loginProgressLayout:RelativeLayout
    lateinit var tvRegister:TextView
    lateinit var etEmail:EditText
    private lateinit var etPassword:EditText
    lateinit var btnLogin:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        btnLogin=findViewById(R.id.btnLogin)
        tvRegister=findViewById(R.id.tvRegister)
        loginProgressLayout=findViewById(R.id.loginProgressLayout)

        mAuth = FirebaseAuth.getInstance();

        tvRegister.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {

            loginProgressLayout.visibility= View.VISIBLE
            val email=etEmail.text.toString()
            val pass=etPassword.text.toString()
            mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        loginProgressLayout.visibility= View.GONE
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.w("message", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
        }
    }
}