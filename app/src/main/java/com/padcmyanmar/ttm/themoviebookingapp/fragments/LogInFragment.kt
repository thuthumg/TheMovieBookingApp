package com.padcmyanmar.ttm.themoviebookingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.delegate.LogInSignInDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewpods.LogInSingInButtonViewPod
import kotlinx.android.synthetic.main.fragment_log_in.*


class LogInFragment(val mDelegate: LogInSignInDelegate) : Fragment() {

    lateinit var mViewPodLogInSignInButtonViewPod: LogInSingInButtonViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mViewPodLogInSignInButtonViewPod = viewPodLogin as LogInSingInButtonViewPod
        mViewPodLogInSignInButtonViewPod.setUpViewPodClickListener(requireContext())
        mViewPodLogInSignInButtonViewPod.setDelegate(mDelegate)

        super.onViewCreated(view, savedInstanceState)
    }



}