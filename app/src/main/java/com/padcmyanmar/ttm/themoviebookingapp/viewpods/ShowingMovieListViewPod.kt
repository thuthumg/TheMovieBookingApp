package com.padcmyanmar.ttm.themoviebookingapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.themoviebookingapp.adapter.ShowingMovieListAdapter
import com.padcmyanmar.ttm.themoviebookingapp.delegate.MovieListDelegate
import kotlinx.android.synthetic.main.view_pod_now_showing.view.*

class ShowingMovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var mDelegate: MovieListDelegate
    lateinit var mShowingMovieListAdapter: ShowingMovieListAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    private fun setDelegate(delegate: MovieListDelegate) {
        this.mDelegate = delegate
    }

    fun setMovieListViewPod(delegate:MovieListDelegate){
        setDelegate(delegate)
        setUpNowShowingRecyclerView()
    }

    fun setUpMovieListTitleViewPod(titleText: String) {
        tvMovieListTitle.text = titleText

    }

    private fun setUpNowShowingRecyclerView() {

        mShowingMovieListAdapter = ShowingMovieListAdapter(mDelegate)

        rvMovieList.adapter = mShowingMovieListAdapter
        rvMovieList.layoutManager =
            LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

    }
}