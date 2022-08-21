package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.BookingDate
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CheckOutVO
import com.padcmyanmar.ttm.themoviebookingapp.network.request.CheckOutRequest
import com.padcmyanmar.ttm.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_movie_list_detail.*
import kotlinx.android.synthetic.main.activity_seating_plan.*
import kotlinx.android.synthetic.main.activity_voucher.*
import kotlinx.android.synthetic.main.activity_voucher.tvMovieTitle
import java.io.Serializable

class VoucherActivity : AppCompatActivity() {
    companion object {
        private const val MOVIE_NAME = "MOVIE_NAME"
        private const val TIMESLOT_VALUE = "TIMESLOT_VALUE"
        private const val CINEMA_NAME = "CINEMA_NAME"
        private const val BOOKING_DATE = "BOOKING_DATE"
        private const val BOOKING_DAY = "BOOKING_DAY"
        private const val CHECKOUT_REQUEST_ID = "CHECKOUT_REQUEST_ID"
        private const val MOVIE_PIC = "MOVIE_PIC"

        fun newIntent(
            context: Context,
            checkOutRequest: String?,
            movieName: String?,
            movieTimeSlotValue: String?,
            cinemaName: String?,
            intentParamMovieBookingDate: String?,
            intentParamMovieBookingDay: String?,
            moviePic: String?

        ): Intent {
//            val bundle = Bundle()
//            bundle.putSerializable(CHECKOUT_REQUEST_ID, checkOutRequest)
            val intent = Intent(context, VoucherActivity::class.java)
            intent.putExtra(CHECKOUT_REQUEST_ID, checkOutRequest)
            intent.putExtra(MOVIE_NAME, movieName)
            intent.putExtra(TIMESLOT_VALUE, movieTimeSlotValue)
            intent.putExtra(CINEMA_NAME, cinemaName)
            intent.putExtra(BOOKING_DATE, intentParamMovieBookingDate)
            intent.putExtra(BOOKING_DAY, intentParamMovieBookingDay)
            intent.putExtra(MOVIE_PIC, moviePic)
            return intent
        }
    }

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    // private var checkOutRequest:CheckOutRequest? = null
    private var checkOutRequest: String? = null
    private var scheckOutRequest: CheckOutRequest? = null
    private var movieName: String? = null
    private var timeslotValue: String? = null
    private var cinemaName: String? = null
    private var bookingDate: String? = null
    private var bookingDay: String? = null
    private var moviePic: String? = null
    private var g = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getIntentParam()
        requestData()
        btnClose.setOnClickListener {
            startActivity(Intent(this, WelcomeLoginActivity::class.java))
            finish()

        }
    }

    private fun requestData() {
        mMovieBookingModel.checkOut(scheckOutRequest,
            onSuccess = { checkOutVO, message ->
                setUpUI(checkOutVO)

                showToast(message)
            },
            onFailure = {
                Log.d("VoucherActivity", "Fail ! = $it")
                showToast(it)
            })
    }

    private fun setUpUI(checkOutVO: CheckOutVO) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL$moviePic")
            .into(ivMoviePicture)

        tvMovieTitle.text = movieName
        tvBookingNumber.text = checkOutVO.bookingNo
        tvShowTimeDate.text = "$timeslotValue- $bookingDate $bookingDay"
        tvTheaterName.text = cinemaName
        tvRow.text = checkOutVO.row
        tvCheckOutSeats.text = checkOutVO.seat
        tvPrice.text = checkOutVO.total

    }


    private fun getIntentParam() {

        checkOutRequest = intent.getStringExtra(CHECKOUT_REQUEST_ID)
        movieName = intent?.getStringExtra(MOVIE_NAME)
        timeslotValue = intent?.getStringExtra(TIMESLOT_VALUE)
        cinemaName = intent?.getStringExtra(CINEMA_NAME)
        bookingDate = intent?.getStringExtra(BOOKING_DATE)
        bookingDay = intent?.getStringExtra(BOOKING_DAY)
        moviePic = intent?.getStringExtra(MOVIE_PIC)
        scheckOutRequest = g.fromJson(checkOutRequest, CheckOutRequest::class.java)

    }

    private fun showToast(it: String) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

}