package com.dicoding.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.githubseconds.FollowerFragment
import com.dicoding.githubseconds.FollowingFragment

class AdapterViewPager(activity: AppCompatActivity, data: Bundle) :
    FragmentStateAdapter(activity) {
    private var bundleFragment: Bundle = data

    //size didapat dari jumlah fragment
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragmentFollow: Fragment? = null
        when (position) {
            0 -> fragmentFollow = FollowerFragment()
            1 -> fragmentFollow = FollowingFragment()
        }
        fragmentFollow?.arguments = this.bundleFragment
        return fragmentFollow as Fragment
    }
}