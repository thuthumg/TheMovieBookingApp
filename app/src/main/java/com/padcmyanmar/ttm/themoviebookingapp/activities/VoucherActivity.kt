package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color

import android.os.Bundle
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
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CheckOutVO
import com.padcmyanmar.ttm.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_voucher.*
import kotlinx.android.synthetic.main.activity_voucher.tvMovieTitle
import java.util.*


class VoucherActivity : AppCompatActivity() {
    companion object {

        private const val CHECKOUT_VO = "CHECKOUT_VO"

        //Booking date param
        private const val MOVIE_BOOKING_DATE = "MOVIE_BOOKING_DATE"
        private const val MOVIE_BOOKING_DAY = "MOVIE_BOOKING_DAY"

        //movie data param
        private const val MOVIE_ID = "MOVIE_ID"
        private const val MOVIE_NAME = "MOVIE_NAME"
        private const val MOVIE_PIC = "MOVIE_PIC"

        //cinema data param
        private const val CINEMA_TIME_SLOT_ID = "CINEMA_TIME_SLOT_ID"
        private const val CINEMA_TIME = "CINEMA_TIME"
        private const val CINEMA_NAME = "CINEMA_NAME"

        //card id param
        private const val CARD_ID = "CARD_ID"

        fun newIntent(
            context: Context,
            checkOutVO: String?,

            movieBookingDate: String?,
            movieBookingDay: String?,

            movieId: Int?,
            movieName: String?,
            moviePic: String?,

            cinemaTimeSlotId: Int?,
            cinemaTime: String?,
            cinemaName: String?,

            cardId: Int?
        ): Intent {

            val intent = Intent(context, VoucherActivity::class.java)
            intent.putExtra(CHECKOUT_VO, checkOutVO)
            intent.putExtra(MOVIE_BOOKING_DATE, movieBookingDate)
            intent.putExtra(MOVIE_BOOKING_DAY, movieBookingDay)

            intent.putExtra(MOVIE_ID, movieId)
            intent.putExtra(MOVIE_NAME, movieName)
            intent.putExtra(MOVIE_PIC, moviePic)

            intent.putExtra(CINEMA_TIME_SLOT_ID, cinemaTimeSlotId)
            intent.putExtra(CINEMA_TIME, cinemaTime)
            intent.putExtra(CINEMA_NAME, cinemaName)

            intent.putExtra(CARD_ID, cardId)
            return intent
        }
    }

    private var checkOutVO: String? = null
    private var scheckOutRequest: CheckOutVO? = null
    private var movieName: String? = null
    private var cinemaTime: String? = null
    private var cinemaName: String? = null
    private var bookingDate: String? = null
    private var bookingDay: String? = null
    private var moviePic: String? = null
    private var cinemaDayTimeSlotId: Int? = null
    private var cardId: Int? = null
    private var movieId: Int? = null
    private var g = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.padcmyanmar.ttm.themoviebookingapp.R.layout.activity_voucher)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getIntentParamAndSetupUI()
        btnClose.setOnClickListener {
            startActivity(Intent(this, WelcomeLoginActivity::class.java))
            finish()

        }
    }

    private fun getIntentParamAndSetupUI() {

        checkOutVO = intent.getStringExtra(CHECKOUT_VO)

        bookingDate = intent?.getStringExtra(MOVIE_BOOKING_DATE)
        bookingDay = intent?.getStringExtra(MOVIE_BOOKING_DAY)

        movieId = intent?.getIntExtra(MOVIE_ID, 0)
        movieName = intent?.getStringExtra(MOVIE_NAME)
        moviePic = intent?.getStringExtra(MOVIE_PIC)

        cinemaDayTimeSlotId = intent?.getIntExtra(CINEMA_TIME_SLOT_ID, 0)
        cinemaTime = intent?.getStringExtra(CINEMA_TIME)
        cinemaName = intent?.getStringExtra(CINEMA_NAME)

        cardId = intent?.getIntExtra(CARD_ID, 0)

        scheckOutRequest = g.fromJson(checkOutVO, CheckOutVO::class.java)
        setUpUI(scheckOutRequest)
    }

    private fun setUpUI(checkOutVO: CheckOutVO?) {

        Glide.with(this)
            .load("$IMAGE_BASE_URL$moviePic")
            .into(ivMoviePicture)

        tvMovieTitle.text = movieName
        tvBookingNumber.text = checkOutVO?.bookingNo
        tvShowTimeDate.text = "$cinemaTime - $bookingDay $bookingDate "
        tvTheaterName.text = cinemaName
        tvRow.text = checkOutVO?.row
        tvCheckOutSeats.text = checkOutVO?.seat
        tvPrice.text = checkOutVO?.total

        //to generate Bar code
        checkOutVO?.qrCode?.let { generateBarCode(it) }
    }


    private fun generateBarCode(s: String) {
        try {
            val hintMap: Hashtable<EncodeHintType, ErrorCorrectionLevel> =
                Hashtable<EncodeHintType, ErrorCorrectionLevel>()
            hintMap[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.L
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
