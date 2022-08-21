package com.padcmyanmar.ttm.themoviebookingapp.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.delegate.LogInSignInDelegate
import com.padcmyanmar.ttm.themoviebookingapp.delegate.LogInWithEmailDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewpods.LogInSingInButtonViewPod
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_log_in.view.*
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.*


class LogInFragment() : Fragment() , LogInSignInDelegate {

    lateinit var mViewPodLogInSignInButtonViewPod: LogInSingInButtonViewPod
    var mLoginWithEmailDelegate: LogInWithEmailDelegate? = null
    var txtEmail: String = ""
    var txtPassword:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
       // mDelegate = context as LogInSignInDelegate
        mLoginWithEmailDelegate = context as LogInWithEmailDelegate
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
        loginEditTextClickListener(view)
        mViewPodLogInSignInButtonViewPod.setDelegate(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onTapConfirm() {
        view?.let {
            if(checkLogInFormValidation(it))
            {
                mLoginWithEmailDelegate?.let {
                    mLoginWithEmailDelegate?.onTapLoginWithEmail(
                        email = txtEmail,
                        password = txtPassword
                    )

                }
            }
        }
    }
    private fun loginEditTextClickListener(view: View) {
        view.txtLogInEmail.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                view.txtLogInEmailInputLayout.error = ""
                view.txtLogInEmailInputLayout.isErrorEnabled = false
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })
        view.txtLogInPassword.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                view.txtLogInPasswordInputLayout.error = ""
                view.txtLogInPasswordInputLayout.isErrorEnabled = false
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })
    }
    private fun checkLogInFormValidation(view: View): Boolean {

        if (view.txtLogInEmail.text.toString().isEmpty() ||
            view.txtLogInPassword.text.toString().isEmpty()
        ) {

            // Empty case
            if (view.txtLogInEmail.text.toString().isEmpty()) {
                view.txtLogInEmailInputLayout.error = "Please Fill Your Email."
                view.txtLogInEmailInputLayout.isErrorEnabled = true
            } else if (view.txtLogInPassword.text.toString().isEmpty()) {
                view.txtLogInPasswordInputLayout.error = "Please Fill Your Password."
                view.txtLogInPasswordInputLayout.isErrorEnabled = true
            }
            return false
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(view.txtLogInEmail.text.toString())
                .matches()) ||
            view.txtLogInPassword.text.toString().count() < 6
        ) {

            // format wrong case
            if (!(Patterns.EMAIL_ADDRESS.matcher(view.txtLogInEmail.text.toString()).matches())) {
                view.txtLogInEmailInputLayout.error = "Invalid Your Email Format."
                view.txtLogInEmailInputLayout.isErrorEnabled = true
            } else if (view.txtLogInPassword.text.toString().count() < 6) {
                view.txtLogInPasswordInputLayout.error =
                    "Your Password must be at least 6 characters."
                view.txtLogInPasswordInputLayout.isErrorEnabled = true
            }
            return false
        } else {
            txtEmail = view.txtLogInEmail.text.toString()
            txtPassword = view.txtLogInPassword.text.toString()
            return true
        }


    }

}