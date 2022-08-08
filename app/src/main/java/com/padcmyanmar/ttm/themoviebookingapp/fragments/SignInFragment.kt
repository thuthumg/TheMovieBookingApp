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
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.*

class SignInFragment(var mDelegate: LogInSignInDelegate) : Fragment() {

    lateinit var mViewPodLogInSignInButtonViewPod: LogInSingInButtonViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mViewPodLogInSignInButtonViewPod = viewPodSignIn as LogInSingInButtonViewPod
        mViewPodLogInSignInButtonViewPod.setUpViewPodClickListener(requireContext())
        mViewPodLogInSignInButtonViewPod.setDelegate(mDelegate)

//        ,
//            mViewPodLogInSignInButtonViewPod.txtInputName.text,
//            mViewPodLogInSignInButtonViewPod.txtInputEmail.text,
//            mViewPodLogInSignInButtonViewPod.txtInputPhone.text,
//            mViewPodLogInSignInButtonViewPod.txtInputPassword.text
        super.onViewCreated(view, savedInstanceState)
    }

}