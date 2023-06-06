package com.example.testnews.fragments.mainpage.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testnews.fragments.mainpage.newspage.NewsFragment
import com.example.testnews.fragments.mainpage.userpage.UserFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> NewsFragment()
            else -> UserFragment()
        }
    }


}