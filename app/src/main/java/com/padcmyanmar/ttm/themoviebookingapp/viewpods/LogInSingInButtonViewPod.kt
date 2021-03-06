package com.padcmyanmar.ttm.themoviebookingapp.viewpods

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.LinearLayout
import com.padcmyanmar.ttm.themoviebookingapp.activities.MovieListActivity
import com.padcmyanmar.ttm.themoviebookingapp.delegate.LogInSignInDelegate
import com.padcmyanmar.ttm.themoviebookingapp.delegate.MovieListDelegate
import kotlinx.android.synthetic.main.login_sign_btn_view_pod.view.*

class LogInSingInButtonViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    lateinit var mDelegate: LogInSignInDelegate

    override fun onFinishInflate() {

        super.onFinishInflate()
    }
    fun setDelegate(delegate: LogInSignInDelegate) {
        this.mDelegate = delegate
    }

    fun setUpViewPodClickListener(context: Context) {

        btnConfirm.setOnClickListener {
            mDelegate.onTapGoToMovieListPage()
                // context.startActivity(Intent(context,MovieListActivity::class.java))
        }
        btnFacebook.setOnClickListener{
            mDelegate.onTapGoToMovieListPage()
           // context.startActivity(Intent(context,MovieListActivity::class.java))
        }
        btnGoogle.setOnClickListener {
            mDelegate.onTapGoToMovieListPage()
           // context.startActivity(Intent(context,MovieListActivity::class.java))
        }
    }

}