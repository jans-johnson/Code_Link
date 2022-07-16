package com.jns.codelink.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.jns.codelink.R
import com.jns.codelink.fragments.*


class MainActivity : AppCompatActivity() {


    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth=FirebaseAuth.getInstance();
        val currentUser= mAuth!!.currentUser
        //only for checking to be deleted later
        if (currentUser==null) {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val addedProjectsFragment=AddedProjectsFragment()
        val chatFragment=ChatFragment()
        val notificationFragment=NotificationFragment()
        val profileFragment=ProfileFragment(0,null)
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val swipeFragment=SwipeFragment()

        setCurrentFragment(swipeFragment)
        bottomNavigationView.getMenu().findItem(R.id.m_swipe).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.m_added->setCurrentFragment(addedProjectsFragment)
                R.id.m_chat->setCurrentFragment(chatFragment)
                R.id.m_swipe->setCurrentFragment(swipeFragment)
                R.id.m_notification->setCurrentFragment(notificationFragment)
                R.id.m_profile->setCurrentFragment(profileFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout,fragment)
            commit()
        }
}