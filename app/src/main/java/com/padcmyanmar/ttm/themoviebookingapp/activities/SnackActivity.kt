package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.SnackAdapter
import com.padcmyanmar.ttm.themoviebookingapp.adapter.PaymentMethodAdapter
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.PaymentMethodVO
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.SnackVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.PaymentMethodDelegate
import com.padcmyanmar.ttm.themoviebookingapp.delegate.SnackDelegate
import com.padcmyanmar.ttm.themoviebookingapp.network.request.CheckOutRequest
import kotlinx.android.synthetic.main.activity_snack.*


class SnackActivity : AppCompatActivity(), PaymentMethodDelegate, SnackDelegate {

    companion object {

        private const val MOVIE_ID = "MOVIE_ID"
        private const val MOVIE_DATE = "MOVIE_DATE"
        private const val TIMESLOT_ID = "TIMESLOT_ID"
        private const val MOVIE_NAME = "MOVIE_NAME"
        private const val TIMESLOT_VALUE = "TIMESLOT_VALUE"
        private const val CINEMA_NAME = "CINEMA_NAME"
        private const val BOOKING_DATE = "BOOKING_DATE"
        private const val BOOKING_DAY = "BOOKING_DAY"
        private const val SEATS_DATA = "SEATS_DATA"
        private const val ROW_DATA = "ROW_DATA"
        private const val PAY_AMOUNT = "PAY_AMOUNT"
        private const val CINEMA_ID = "CINEMA_ID"
        private const val MOVIE_PIC = "MOVIE_PIC"
        fun newIntent(
            context: Context,
            movieId: Int?,
            movieDateParam: String?,
            movieTimeSlotId: Int?,
            movieName: String?,
            movieTimeSlotValue: String?,
            cinemaName: String?,
            intentParamMovieBookingDate: String?,
            intentParamMovieBookingDay: String?,
            intentParamSeats: String?,
            intentParamRow: String?,
            toBuyTicketAmt: Double?,
            cinemaId: Int?,
            moviePic: String?
        ): Intent {
            val intent = Intent(context, SnackActivity::class.java)

            intent.putExtra(MOVIE_ID, movieId)
            intent.putExtra(MOVIE_DATE, movieDateParam)
            intent.putExtra(TIMESLOT_ID, movieTimeSlotId)
            intent.putExtra(MOVIE_NAME, movieName)
            intent.putExtra(TIMESLOT_VALUE, movieTimeSlotValue)
            intent.putExtra(CINEMA_NAME, cinemaName)
            intent.putExtra(BOOKING_DATE, intentParamMovieBookingDate)
            intent.putExtra(BOOKING_DAY, intentParamMovieBookingDay)
            intent.putExtra(SEATS_DATA, intentParamSeats)
            intent.putExtra(ROW_DATA, intentParamRow)
            intent.putExtra(PAY_AMOUNT, toBuyTicketAmt)
            intent.putExtra(CINEMA_ID, cinemaId)
            intent.putExtra(MOVIE_PIC, moviePic)
            return intent
        }
    }

    lateinit var snackAapter: SnackAdapter
    lateinit var mPaymentMethodAdapter: PaymentMethodAdapter
    var payAmount: Double? = 0.00
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    var mSnackList: List<SnackVO> = listOf()
    var mPaymentMethodList: List<PaymentMethodVO> = listOf()
    var mTotalPayAmount: Double? = 0.00
    private var movieId: Int? = null
    private var movieBookingDate: String? = null
    private var movieBookingTimeSlotId: Int? = null
    private var movieName: String? = null
    private var timeslotValue: String? = null
    private var cinemaName: String? = null
    private var bookingDate: String? = null
    private var bookingDay: String? = null
    private var cinemaId: Int? = null
    private var seatsData: String? = null
    private var rowData: String? = null
    private var moviePic: String? = null
    private var intentParamSnackVO: SnackVO? = null
    private var intentParamSelectedSnackVOList: ArrayList<SnackVO>? = arrayListOf()
    private var requestParamSnackVO: SnackVO? = null
    private var requestParamSelectedSnackVOList: ArrayList<SnackVO>? = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        getIntentParam()
        setUpSnackAdapter()
        setUpPaymentMethodAdapter()
        setUpClickListener()
        requestData()
    }

    private fun requestData() {

        //Snack List
        mMovieBookingModel.getSnackList(
            onSuccess = {
                mSnackList = it

                snackAapter.setData(mSnackList)

            },
            onFailure = {
                showError(it)
            }
        )

        //Payment Method List
        mMovieBookingModel.getPaymentMethodList(
            onSuccess = {
                mPaymentMethodList = it
                mPaymentMethodAdapter.setData(mPaymentMethodList)

            },
            onFailure = {
                showError(it)
            }
        )
    }

    private fun getIntentParam() {

        movieId = intent?.getIntExtra(MOVIE_ID, 0)
        movieBookingDate = intent?.getStringExtra(MOVIE_DATE)
        movieBookingTimeSlotId = intent?.getIntExtra(TIMESLOT_ID, 0)
        movieName = intent?.getStringExtra(MOVIE_NAME)
        timeslotValue = intent?.getStringExtra(TIMESLOT_VALUE)
        cinemaName = intent?.getStringExtra(CINEMA_NAME)
        bookingDate = intent?.getStringExtra(BOOKING_DATE)
        bookingDay = intent?.getStringExtra(BOOKING_DAY)
        cinemaId = intent?.getIntExtra(CINEMA_ID, 0)
        seatsData = intent?.getStringExtra(SEATS_DATA)
        rowData = intent?.getStringExtra(ROW_DATA)
        payAmount = intent?.getDoubleExtra(PAY_AMOUNT, 0.00)
        moviePic = intent?.getStringExtra(MOVIE_PIC)
        mTotalPayAmount = payAmount
        btnPay.text = String.format(getString(R.string.txt_pay_amount), payAmount)
        tvSubTotal.text = String.format(getString(R.string.txt_sub_total_pay_amount), payAmount)

        Log.d(
            "SnackActivity",
            "check seatsData and bookingdate-bookingday = $seatsData /// $bookingDate - $bookingDay"
        )
    }

    private fun setUpClickListener() {
        btnBack.setOnClickListener {

            onBackPressed()
        }
        btnPay.setOnClickListener {

            if(intentParamSelectedSnackVOList?.size == 0)
            {
                showError("Please select the snack")
            }else{
                intentParamSelectedSnackVOList?.forEach {
                    requestParamSnackVO = SnackVO(
                        id = it.id,
                        name = "",
                        description = "",
                        price = 0,
                        image = "",
                        quantity = it.quantity
                    )
                    requestParamSnackVO?.let { requestSnackVO ->
                        requestParamSelectedSnackVOList?.add(requestSnackVO)
                    }

                    Log.d("snack", "check snack list = $it //// $seatsData")
                }
                var checkOutRequest: CheckOutRequest = CheckOutRequest(
                    cinemaDayTimeslotId = movieBookingTimeSlotId,
                    row = rowData,
                    seatNumber = seatsData,
                    bookingDate = movieBookingDate,
                    totalPrice = mTotalPayAmount?.toInt(),
                    movieId = movieId,
                    cardId = 0,
                    cinemaId = cinemaId,
                    snacks = requestParamSelectedSnackVOList
                )

// to change json string
                val gson = Gson()
                val gsonPretty = GsonBuilder().setPrettyPrinting().create()
                val tut = checkOutRequest
                val jsonTut: String = gson.toJson(tut)
                println(jsonTut)
                val checkoutRequestJson: String = gsonPretty.toJson(tut)
                println(checkoutRequestJson)


                // checkOutRequest = checkOutRequest,
                startActivity(
                    PaymentConfirmActivity.newIntent(
                        this,
                        checkOutRequest = checkoutRequestJson,
                        movieName = movieName,
                        movieTimeSlotValue = timeslotValue,
                        cinemaName = cinemaName,
                        intentParamMovieBookingDate = bookingDate,
                        intentParamMovieBookingDay = bookingDay,
                        moviePic = moviePic
                    )

                )
            }


        }

    }

    private fun setUpPaymentMethodAdapter() {
        mPaymentMethodAdapter = PaymentMethodAdapter(this)
        rvPaymentMethod.adapter = mPaymentMethodAdapter
        rvPaymentMethod.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun setUpSnackAdapter() {

        snackAapter = SnackAdapter(this)
        rvComboSet.adapter = snackAapter
        rvComboSet.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun showError(it: String) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    override fun onTapPaymentMethod(mPaymentMethodVO: PaymentMethodVO?) {
        mPaymentMethodList.forEach {

            it.isSelected = mPaymentMethodVO?.id == it.id

        }
        mPaymentMethodAdapter.setData(mPaymentMethodList)

    }

    override fun onTapMinus(snackVO: SnackVO?) {
        var snackAmtArray: ArrayList<Double> = arrayListOf()
        intentParamSelectedSnackVOList = arrayListOf()
        var mTotalSnackAmt = 0.00

        mSnackList.forEach { snackItem ->

            if (snackVO?.id == snackItem.id) {

                if (snackItem.quantity > 0) {
                    snackItem.quantity = snackItem.quantity.minus(1)

                } else {
                    snackItem.quantity = snackItem.quantity
                }


            } else {
                snackItem.quantity = snackItem.quantity
            }
            snackItem.calculateTotalPayAmt()?.let {
                snackAmtArray.add(it)
            }

            if (snackItem.quantity > 0) {
                intentParamSnackVO = snackItem
                intentParamSnackVO?.let { intentParamSelectedSnackVOList?.add(it) }
            }
        }

        snackAapter.setData(mSnackList) // refresh snack list

        //bind Amount data
        snackAmtArray.forEach {
            mTotalSnackAmt = mTotalSnackAmt.plus(it)
        }
        btnPay.text = String.format(getString(R.string.txt_pay_amount),
            payAmount?.let { mTotalSnackAmt.plus(it) })
        tvSubTotal.text = String.format(getString(R.string.txt_sub_total_pay_amount),
            payAmount?.let { mTotalSnackAmt.plus(it) })

        mTotalPayAmount =
            payAmount?.let { mTotalSnackAmt.plus(it) } // to add another page as a param


    }

    override fun onTapPlus(snackVO: SnackVO?) {
        var snackAmtArray: ArrayList<Double> = arrayListOf()
        intentParamSelectedSnackVOList = arrayListOf()
        var mTotalSnackAmt = 0.00
        mSnackList.forEach { snackItem ->

            if (snackVO?.id == snackItem.id) {
                snackItem.quantity = snackItem.quantity.plus(1)

            } else {
                snackItem.quantity = snackItem.quantity

            }
            snackItem.calculateTotalPayAmt()?.let {
                snackAmtArray.add(it)
            }

            if (snackItem.quantity > 0) {
                intentParamSnackVO = snackItem
                intentParamSnackVO?.let { intentParamSelectedSnackVOList?.add(it) }
            }

        }

        snackAapter.setData(mSnackList) // refresh snack list

        //bind Amount data
        snackAmtArray.forEach {
            mTotalSnackAmt = mTotalSnackAmt.plus(it)
        }

        btnPay.text = String.format(getString(R.string.txt_pay_amount),
            payAmount?.let { mTotalSnackAmt.plus(it) })
        tvSubTotal.text = String.format(getString(R.string.txt_sub_total_pay_amount),
            payAmount?.let { mTotalSnackAmt.plus(it) })

        mTotalPayAmount =
            payAmount?.let { mTotalSnackAmt.plus(it) } // to add another page as a param
    }


}