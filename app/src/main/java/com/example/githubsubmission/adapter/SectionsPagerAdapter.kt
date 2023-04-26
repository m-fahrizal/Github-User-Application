package com.example.githubsubmission.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubsubmission.FollowFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var username: String = ""

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowFragment.ARG_POSITION, position + 1)
            putString(FollowFragment.ARG_USERNAME, username)
        }
        return fragment
    }
    override fun getItemCount(): Int {
        return 2
    }
}

//class SectionsPagerAdapter(fragmentManager: FragmentManager, private val username: String) :
//    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//
//    override fun getItem(position: Int): Fragment {
//        // Mendapatkan fragment berdasarkan posisi
//        // Kirimkan data posisi dan username ke fragment yang akan ditampilkan
//        return when (position) {
//            0 -> FollowersFragment.newInstance(position, username)
//            1 -> FollowingFragment.newInstance(position, username)
//            else -> throw IllegalArgumentException("Invalid position: $position")
//        }
//    }
//
//    override fun getCount(): Int {
//        // Jumlah fragment yang akan ditampilkan
//        return 2
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        // Nama tab yang akan ditampilkan di TabLayout
//        return when (position) {
//            0 -> "Followers"
//            1 -> "Following"
//            else -> throw IllegalArgumentException("Invalid position: $position")
//        }
//    }
//}
