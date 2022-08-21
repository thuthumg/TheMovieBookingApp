package com.padcmyanmar.ttm.themoviebookingapp.fragments

import android.content.Context
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.delegate.LogInSignInDelegate
import com.padcmyanmar.ttm.themoviebookingapp.delegate.RegisterDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewpods.LogInSingInButtonViewPod
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.*

class SignInFragment() : Fragment(),LogInSignInDelegate {

    lateinit var mViewPodLogInSignInButtonViewPod: LogInSingInButtonViewPod
    var mRegisterDelegate:RegisterDelegate? = null
    var txtEmail:String = ""
    var txtPassword:String = ""
    var txtPhone:String = ""
    var txtName:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
       // mDelegate = context as LogInSignInDelegate
        mRegisterDelegate = context as RegisterDelegate
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
        signInEditTextClickListener(view)

        mViewPodLogInSignInButtonViewPod.setDelegate(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onTapConfirm() {
            Log.d("SignInFragment","check onTapConfirm")
            view?.let {
                    if(checkSignInFormValidation(it))
                    {
                        mRegisterDelegate?.let {
                            mRegisterDelegate?.onTapRegister(
                                name = txtName,
                                phone = txtPhone,
                                email = txtEmail,
                                password = txtPassword
                            )

                        }
                    }
            }


    }
    private fun signInEditTextClickListener(view: View) {
        view.txtSignInName.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                view.txtSignInNameInputLayout.error = ""
                view.txtSignInNameInputLayout.isErrorEnabled = false
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

        view.txtSignInPhone.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                view.txtSignInPhoneInputLayout.error = ""
                view.txtSignInPhoneInputLayout.isErrorEnabled = false
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

        view.txtSignInEmail.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                view.txtSignInEmailInputLayout.error = ""
                view.txtSignInEmailInputLayout.isErrorEnabled = false
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

        view.txtSignInPassword.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                view.txtSignInPasswordInputLayout.error = ""
                view.txtSignInPasswordInputLayout.isErrorEnabled = false
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
    private fun checkSignInFormValidation(view: View): Boolean {

        if (view.txtSignInName.text.toString().isEmpty() ||
            view.txtSignInPhone.text.toString().isEmpty() ||
            view.txtSignInEmail.text.toString().isEmpty() ||
            view.txtSignInPassword.text.toString().isEmpty()
        ) {

            // Empty case
            if (view.txtSignInName.text.toString().isEmpty()) {
                view.txtSignInNameInputLayout.error = "Please Fill Your Name."
                view.txtSignInNameInputLayout.isErrorEnabled = true
            } else if (view.txtSignInPhone.text.toString().isEmpty()) {
                view.txtSignInPhoneInputLayout.error = "Please Fill Your Phone."
                view.txtSignInPhoneInputLayout.isErrorEnabled = true
            } else
                if (view.txtSignInEmail.text.toString().isEmpty()) {
                    view.txtSignInEmailInputLayout.error = "Please Fill Your Email."
                    view.txtSignInEmailInputLayout.isErrorEnabled = true
                } else if (view.txtSignInPassword.text.toString().isEmpty()) {
                    view.txtSignInPasswordInputLayout.error = "Please Fill Your Password."
                    view.txtSignInPasswordInputLayout.isErrorEnabled = true
                }
            return false
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(view.txtSignInEmail.text.toString()).matches())
            || view.txtSignInPassword.text.toString().count() < 6 ||
            !(PhoneNumberUtils.isGlobalPhoneNumber(view.txtSignInPhone.text.toString()))
        ) {

            // format wrong case
            if (!(PhoneNumberUtils.isGlobalPhoneNumber(view.txtSignInPhone.text.toString()))) {
                view.txtSignInPhoneInputLayout.error = "Invalid Your Phone Format."
                view.txtSignInPhoneInputLayout.isErrorEnabled = true
            } else
                if (!(Patterns.EMAIL_ADDRESS.matcher(view.txtSignInEmail.text.toString())
                        .matches())
                ) {
                    view.txtSignInEmailInputLayout.error = "Invalid Your Email Format."
                    view.txtSignInEmailInputLayout.isErrorEnabled = true
                } else if (view.txtSignInPassword.text.toString().count() < 6) {
                    view.txtSignInPasswordInputLayout.error =
                        "Your Password must be at least 6 characters."
                    view.txtSignInPasswordInputLayout.isErrorEnabled = true
                }
            return false
        } else {
            txtName = view.txtSignInName.text.toString()
            txtEmail = view.txtSignInEmail.text.toString()
            txtPassword = view.txtSignInPassword.text.toString()
            txtPhone = view.txtSignInPhone.text.toString()
            return true
        }

    }

}