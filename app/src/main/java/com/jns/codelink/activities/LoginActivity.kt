package com.jns.codelink.activities

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.jns.codelink.R


class LoginActivity : AppCompatActivity() {

    lateinit var mAuth:FirebaseAuth

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
        mAuth = FirebaseAuth.getInstance();

        tvRegister.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val email=etEmail.text.toString()
            val pass=etPassword.text.toString()
            mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        Log.d("jans",user?.email.toString())
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