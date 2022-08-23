package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.Writer
import com.google.zxing.common.BitMatrix
import com.google.zxing.oned.Code128Writer
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CheckOutVO
import com.padcmyanmar.ttm.themoviebookingapp.network.request.CheckOutRequest
import com.padcmyanmar.ttm.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_voucher.*
import kotlinx.android.synthetic.main.activity_voucher.tvMovieTitle
import java.util.*
import kotlin.collections.HashMap


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
    private var checkOutVO: String? = null
    private var scheckOutRequest: CheckOutVO? = null
    private var movieName: String? = null
    private var timeslotValue: String? = null
    private var cinemaName: String? = null
    private var bookingDate: String? = null
    private var bookingDay: String? = null
    private var moviePic: String? = null
    private var g = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.padcmyanmar.ttm.themoviebookingapp.R.layout.activity_voucher)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getIntentParam()
        btnClose.setOnClickListener {
            startActivity(Intent(this, WelcomeLoginActivity::class.java))
            finish()

        }
    }

//    private fun requestData() {
//        mMovieBookingModel.checkOut(scheckOutRequest,
//            onSuccess = { checkOutVO, message ->
//                setUpUI(checkOutVO)
//
//                showToast(message)
//            },
//            onFailure = {
//                Log.d("VoucherActivity", "Fail ! = $it")
//                showToast(it)
//            })
//    }

    private fun setUpUI(checkOutVO: CheckOutVO?) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL$moviePic")
            .into(ivMoviePicture)

        tvMovieTitle.text = movieName
        tvBookingNumber.text = checkOutVO?.bookingNo
        tvShowTimeDate.text = "$timeslotValue - $bookingDay $bookingDate "
        tvTheaterName.text = cinemaName
        tvRow.text = checkOutVO?.row
        tvCheckOutSeats.text = checkOutVO?.seat
        tvPrice.text = checkOutVO?.total

        val hintMap: MutableMap<EncodeHintType, ErrorCorrectionLevel> = HashMap()
        hintMap[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
        checkOutVO?.qrCode?.let { generateBarCode(it) }
       // generateBarCode(checkOutVO.qrCode)
    }


    private fun getIntentParam() {

        checkOutVO = intent.getStringExtra(CHECKOUT_REQUEST_ID)
        movieName = intent?.getStringExtra(MOVIE_NAME)
        timeslotValue = intent?.getStringExtra(TIMESLOT_VALUE)
        cinemaName = intent?.getStringExtra(CINEMA_NAME)
        bookingDate = intent?.getStringExtra(BOOKING_DATE)
        bookingDay = intent?.getStringExtra(BOOKING_DAY)
        moviePic = intent?.getStringExtra(MOVIE_PIC)
        scheckOutRequest = g.fromJson(checkOutVO, CheckOutVO::class.java)
        setUpUI(scheckOutRequest)
    }

    private fun generateBarCode(s: String) {
        try {
         //   val productId = editTextProductId!!.text.toString()
            val hintMap: Hashtable<EncodeHintType, ErrorCorrectionLevel> =
                Hashtable<EncodeHintType, ErrorCorrectionLevel>()
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L)
            val codeWriter: Writer
            codeWriter = Code128Writer()
            val byteMatrix: BitMatrix =
                codeWriter.encode(s, BarcodeFormat.CODE_128, 400, 200, hintMap)
            val width = byteMatrix.width
            val height = byteMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            for (i in 0 until width) {
                for (j in 0 until height) {
                    bitmap.setPixel(i, j, if (byteMatrix[i, j]) Color.BLACK else Color.WHITE)
                }
            }
            ivBarCode.setImageBitmap(bitmap)
        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
        }

    }


}
