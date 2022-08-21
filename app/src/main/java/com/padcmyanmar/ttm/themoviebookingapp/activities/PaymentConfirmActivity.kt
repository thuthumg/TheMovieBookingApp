package com.padcmyanmar.ttm.themoviebookingapp.activities


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.gson.Gson
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.CardListCarouselAdapter
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CardsVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.PaymentCardDelegate
import com.padcmyanmar.ttm.themoviebookingapp.network.request.CheckOutRequest
import kotlinx.android.synthetic.main.activity_payment_confirm.*
import kotlinx.android.synthetic.main.activity_payment_confirm.btnBack
import kotlinx.android.synthetic.main.activity_snack.*
import java.util.*


class PaymentConfirmActivity : AppCompatActivity(), PaymentCardDelegate {

    companion object {
        private const val REQUEST_RESULT = 1
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
            val intent = Intent(context, PaymentConfirmActivity::class.java)
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

    private lateinit var cardListCarouselAdapter: CardListCarouselAdapter

    // var payAmount: Double? = 0.00
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    var mCardsVOList: List<CardsVO>? = listOf()
    var cardNumber: String? = null
    var sCheckOutRequest: CheckOutRequest? = null
    var g: Gson = Gson()

    // private var checkOutRequest:CheckOutRequest? = null
    private var checkOutRequest: String? = null
    private var movieName: String? = null
    private var timeslotValue: String? = null
    private var cinemaName: String? = null
    private var bookingDate: String? = null
    private var bookingDay: String? = null
    private var moviePic: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_confirm)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        getIntentParam()
        setUpCarouselView()
        setUpClickListener()
        requestData(cardNumber)

    }

    private fun requestData(cardNumber: String?) {
        mMovieBookingModel.getProfile(
            onSuccess = { userDataVO ->
                mCardsVOList = userDataVO.cards

                if (cardNumber == null) {
                    userDataVO.cards?.let { cardListCarouselAdapter.setData(it) }
                } else {
                    setUpCarouselView()//
                    mCardsVOList = userDataVO.cards?.reversed()
                    mCardsVOList?.let { cardListCarouselAdapter.setData(it) }
                }

            },
            onFailure = {
                showError(it)
            }
        )
    }

    private fun getIntentParam() {

        // checkOutRequest = intent.getSerializableExtra(CHECKOUT_REQUEST_ID) as CheckOutRequest?
        checkOutRequest = intent?.getStringExtra(CHECKOUT_REQUEST_ID)
        movieName = intent?.getStringExtra(MOVIE_NAME)
        timeslotValue = intent?.getStringExtra(TIMESLOT_VALUE)
        cinemaName = intent?.getStringExtra(CINEMA_NAME)
        bookingDate = intent?.getStringExtra(BOOKING_DATE)
        bookingDay = intent?.getStringExtra(BOOKING_DAY)
        moviePic = intent?.getStringExtra(MOVIE_PIC)


        sCheckOutRequest = g.fromJson(checkOutRequest, CheckOutRequest::class.java)

        tvPaymentAmount.text = "$ ${sCheckOutRequest?.totalPrice}"
    }

    private fun setUpCarouselView() {
        cardListCarouselAdapter = CardListCarouselAdapter(this)
        carouselRecyclerview.adapter = cardListCarouselAdapter

    }


    private fun setUpClickListener() {
        btnConfirm.setOnClickListener {
            // startActivity(Intent(this,VoucherActivity::class.java))
            //  sCheckOutRequest?.cardId = 1196

            val str: String = g.toJson(sCheckOutRequest)
            startActivity(
                VoucherActivity.newIntent(
                    this,
                    checkOutRequest = str,
                    movieName = movieName,
                    movieTimeSlotValue = timeslotValue,
                    cinemaName = cinemaName,
                    intentParamMovieBookingDate = bookingDate,
                    intentParamMovieBookingDay = bookingDay,
                    moviePic = moviePic


                )
            )
        }
        btnBack.setOnClickListener {
            onBackPressed()
        }

        rlBtnAddNewCard.setOnClickListener {
            // startActivity(Intent(this, AddCardActivity::class.java))
            startActivityForResult(
                Intent(this, AddCardActivity::class.java),
                REQUEST_RESULT
            )
        }

    }

    override fun onTapPaymentCard(cardsVO: CardsVO?) {
        Toast.makeText(this, "payment card click event", Toast.LENGTH_SHORT).show()
        sCheckOutRequest?.cardId = cardsVO?.id


    }

    private fun showError(it: String) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_RESULT) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                // success
                data.apply {
                    cardNumber = getIntExtra(AddCardActivity.RESULT_DATA, 0).toString()
                    requestData(cardNumber)
                }

            } else {
                // fail
            }
        }
    }
}