package com.padcmyanmar.ttm.themoviebookingapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.padcmyanmar.ttm.themoviebookingapp.delegate.LogInSignInDelegate
import com.padcmyanmar.ttm.themoviebookingapp.fragments.LogInFragment
import com.padcmyanmar.ttm.themoviebookingapp.fragments.SignInFragment

class LogInSignInViewPagerAdapter(fragmentActivity: FragmentActivity,var mDelegate: LogInSignInDelegate) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {

                return LogInFragment(mDelegate)
            }

            1 -> {

                return SignInFragment(mDelegate)
            }
            else -> {

                return LogInFragment(mDelegate)
            }
        }


    }
}