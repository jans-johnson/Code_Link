package com.jns.codelink.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jns.codelink.fragments.AddedListFragment
import com.jns.codelink.fragments.AllChatFragment
import com.jns.codelink.fragments.GroupChatFragment
import com.jns.codelink.fragments.OngoingListFragment

class ChatAdapter(fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return AllChatFragment()
            }
            1 -> {
                return GroupChatFragment()
            }
        }
        throw IllegalStateException("position $position is invalid for this viewpager")
    }

}

