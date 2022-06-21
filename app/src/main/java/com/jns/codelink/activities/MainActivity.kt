package com.jns.codelink.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jns.codelink.R
import com.jns.codelink.fragments.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addedProjectsFragment=AddedProjectsFragment()
        val chatFragment=ChatFragment()
        val notificationFragment=NotificationFragment()
        val profileFragment=ProfileFragment()
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