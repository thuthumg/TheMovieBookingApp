package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.CastListAdapter
import com.padcmyanmar.ttm.themoviebookingapp.adapter.ChipGenreAdapter
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_movie_list_detail.*
import kotlin.math.roundToLong

class MovieListDetailActivity : AppCompatActivity() {

    companion object {

        private const val MOVIE_ID = "MOVIE_ID"

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieListDetailActivity::class.java)
            intent.putExtra(MOVIE_ID, movieId)

            return intent
        }
    }

    lateinit var mCastListAdapter: CastListAdapter
    lateinit var mChipGenreAdapter: ChipGenreAdapter
    private var movieId: Int? = null
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    var mMovieVO: MovieVO? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list_detail)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setUpCastListAdapter()
        setUpChipGenre()
        setUpClickListener()
        getMovieDetailIntentParamAndRequestData()

    }

    private fun getMovieDetailIntentParamAndRequestData() {
        movieId = intent?.getIntExtra(MOVIE_ID, 0)
        movieId?.let {
            requestData(it)
        }

    }

    private fun requestData(movieId: Int) {
        mMovieBookingModel.getMovieDetail(
            movieId = movieId.toString(),
            onSuccess = {
                bindData(it)
            },
            onFailure = {
                showError(it)
            })

        mMovieBookingModel.getCreditsByMovie(movieId = movieId.toString(),
            onSuccess = {
                mCastListAdapter.setData(it.first)
            },
            onFailure = {
                showError(it)
            })
    }

    private fun bindData(movieVO: MovieVO) {
        mMovieVO = movieVO
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movieVO.posterPath}")
            .into(ivMovieDetails)
        collapsingToolbarTitle.title = movieVO.title ?: ""
        tvMovieDetailName.text = movieVO.title ?: ""
        tvMovieHour.text = movieVO.calculateMovieTimeMinutesToHours()
        rbMovieRating.rating = movieVO.getRatingBasedOnFiveStars()
        movieVO.voteAverage?.let { voteAverage ->
            tvRatingCount.text = "IMDb ${(voteAverage * 10.0).roundToLong() / 10.0}"
        }
        mChipGenreAdapter.setData(movieVO.genres ?: listOf())

        tvPlotSummaryDesc.text = movieVO.overview ?: ""

    }

    private fun setUpChipGenre() {

        mChipGenreAdapter = ChipGenreAdapter()
        rvGenreChip.adapter = mChipGenreAdapter
        rvGenreChip.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpClickListener() {
        btnBack.setOnClickListener {
            onBackPressed()
        }
        btnGetTicket.setOnClickListener {
            startActivity(movieId?.let { it1 ->
                mMovieVO?.posterPath?.let { it2 ->
                    BookingDateTimeActivity.newIntent(
                        context = this,
                        movieId = it1,
                        movieTitle = mMovieVO?.title.toString(),
                        moviePic = it2
                    )
                }
            })

        }
    }

    private fun setUpCastListAdapter() {
        mCastListAdapter = CastListAdapter()
        rvCastList.adapter = mCastListAdapter
        rvCastList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun showError(it: String) {

        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()


    }

}