package com.jns.codelink.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.Transaction
import com.jns.codelink.R
import com.jns.codelink.fragments.*
import kotlinx.coroutines.Delay

class SplashActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashpg)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent =Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        },1000 )
    }

}