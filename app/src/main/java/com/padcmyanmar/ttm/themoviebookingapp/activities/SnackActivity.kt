package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.SnackAdapter
import com.padcmyanmar.ttm.themoviebookingapp.adapter.PaymentMethodAdapter
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.PaymentMethodVO
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.SnackVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.PaymentMethodDelegate
import com.padcmyanmar.ttm.themoviebookingapp.delegate.SnackDelegate
import com.padcmyanmar.ttm.themoviebookingapp.utils.toChangeJsonString
import kotlinx.android.synthetic.main.activity_snack.*


class SnackActivity : AppCompatActivity(), PaymentMethodDelegate, SnackDelegate {

    companion object {

        private const val REQUEST_RESULT = 1
        const val RESULT_DATA = "RESULT_DATA"


        //Booking date data param
        private const val MOVIE_BOOKING_DATE_YMD_FORMAT =
            "MOVIE_BOOKING_DATE_YMD_FORMAT" // 2022-08-26
        private const val MOVIE_BOOKING_DATE = "MOVIE_BOOKING_DATE" // Tue
        private const val MOVIE_BOOKING_DAY = "MOVIE_BOOKING_DAY" //03

        //movie data param
        private const val MOVIE_ID = "MOVIE_ID"
        private const val MOVIE_NAME = "MOVIE_NAME"
        private const val MOVIE_PIC = "MOVIE_PIC"

        //cinema data param
        private const val CINEMA_TIMESLOT_ID = "CINEMA_TIMESLOT_ID"
        private const val CINEMA_TIME = "CINEMA_TIME"
        private const val CINEMA_NAME = "CINEMA_NAME"
        private const val CINEMA_ID = "CINEMA_ID"

        //seat data param and amount data param
        private const val SEATS_DATA = "SEATS_DATA"
        private const val ROW_DATA = "ROW_DATA"
        private const val TICKET_AMOUNT = "TICKET_AMOUNT"

        fun newIntent(
            context: Context,
            movieBookingDateYMDFormat: String?,
            movieBookingDate: String?,
            movieBookingDay: String?,

            movieId: Int?,
            movieName: String?,
            moviePic: String?,

            cinemaTimeSlotId: Int?,
            cinemaTime: String?,
            cinemaName: String?,
            cinemaId: Int?,

            seatsData: String?,
            rowData: String?,
            ticketAmt: Double?


        ): Intent {
            val intent = Intent(context, SnackActivity::class.java)

            //Booking date data param
            intent.putExtra(MOVIE_BOOKING_DATE_YMD_FORMAT, movieBookingDateYMDFormat) // 2022-08-26
            intent.putExtra(MOVIE_BOOKING_DATE, movieBookingDate)
            intent.putExtra(MOVIE_BOOKING_DAY, movieBookingDay)

            //movie data param
            intent.putExtra(MOVIE_ID, movieId)
            intent.putExtra(MOVIE_NAME, movieName)
            intent.putExtra(MOVIE_PIC, moviePic)

            //cinema data param
            intent.putExtra(CINEMA_TIMESLOT_ID, cinemaTimeSlotId)
            intent.putExtra(CINEMA_TIME, cinemaTime)
            intent.putExtra(CINEMA_ID, cinemaId)
            intent.putExtra(CINEMA_NAME, cinemaName)

            //seat data param and amount data param
            intent.putExtra(SEATS_DATA, seatsData)
            intent.putExtra(ROW_DATA, rowData)
            intent.putExtra(TICKET_AMOUNT, ticketAmt)
            return intent
        }
    }

    lateinit var snackAapter: SnackAdapter
    lateinit var mPaymentMethodAdapter: PaymentMethodAdapter
    var ticketAmount: Double? = 0.00
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    var mSnackList: List<SnackVO> = listOf()
    var mPaymentMethodList: List<PaymentMethodVO> = listOf()
    var mTotalPayAmount: Double? = 0.00

    private var movieBookingDateYMDFormat: String? = null
    private var movieBookingDate: String? = null
    private var movieBookingDay: String? = null

    private var movieId: Int? = null
    private var movieName: String? = null
    private var moviePic: String? = null

    private var cinemaTimeSlotId: Int? = null
    private var cinemaTime: String? = null
    private var cinemaName: String? = null
    private var cinemaId: Int? = null

    private var seatsData: String? = null
    private var rowData: String? = null

    private var intentParamSnackVO: SnackVO? = null
    private var intentParamSelectedSnackVOList: ArrayList<SnackVO>? = arrayListOf()
    private var requestParamSnackVO: SnackVO? = null
    private var requestParamSelectedSnackVOList: ArrayList<SnackVO>? = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        getIntentParam()
        setUpTotalAmt()
        setUpSnackAdapter()
        setUpPaymentMethodAdapter()
        setUpClickListener()
        requestData()
    }

    private fun getIntentParam() {

        //Booking date data param
        movieBookingDateYMDFormat = intent?.getStringExtra(MOVIE_BOOKING_DATE_YMD_FORMAT)
        movieBookingDate = intent?.getStringExtra(MOVIE_BOOKING_DATE)
        movieBookingDay = intent?.getStringExtra(MOVIE_BOOKING_DAY)

        //movie data param
        movieId = intent?.getIntExtra(MOVIE_ID, 0)
        movieName = intent?.getStringExtra(MOVIE_NAME)
        moviePic = intent?.getStringExtra(MOVIE_PIC)

        //cinema data param
        cinemaTimeSlotId = intent?.getIntExtra(CINEMA_TIMESLOT_ID, 0)
        cinemaTime = intent?.getStringExtra(CINEMA_TIME)
        cinemaName = intent?.getStringExtra(CINEMA_NAME)
        cinemaId = intent?.getIntExtra(CINEMA_ID, 0)

        //seat data param and amount data param
        seatsData = intent?.getStringExtra(SEATS_DATA)
        rowData = intent?.getStringExtra(ROW_DATA)
        ticketAmount = intent?.getDoubleExtra(TICKET_AMOUNT, 0.00)

        mTotalPayAmount = ticketAmount


    }

    private fun setUpTotalAmt() {
        btnPay.text = String.format(getString(R.string.txt_pay_amount), ticketAmount)
        tvSubTotal.text = String.format(getString(R.string.txt_sub_total_pay_amount), ticketAmount)
    }

    private fun setUpSnackAdapter() {

        snackAapter = SnackAdapter(this)
        rvComboSet.adapter = snackAapter
        rvComboSet.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun setUpPaymentMethodAdapter() {
        mPaymentMethodAdapter = PaymentMethodAdapter(this)
        rvPaymentMethod.adapter = mPaymentMethodAdapter
        rvPaymentMethod.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun setUpClickListener() {

        btnBack.setOnClickListener {

            onBackPressed()
        }

        btnPay.setOnClickListener {
            requestParamSelectedSnackVOList = arrayListOf()
            if (intentParamSelectedSnackVOList?.size == 0) {
                showError("Please select the snack")
            } else {
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
                }

                val snackJsonString: String = toChangeJsonString(requestParamSelectedSnackVOList)

                Log.d("SanckActivity","check snack list $snackJsonString")
                startActivityForResult(
                    PaymentConfirmActivity.newIntent(
                        this,

                        movieBookingDateYMDFormat = movieBookingDateYMDFormat,
                        movieBookingDate = movieBookingDate,
                        movieBookingDay = movieBookingDay,

                        movieId = movieId,
                        movieName = movieName,
                        moviePic = moviePic,

                        cinemaTimeSlotId = cinemaTimeSlotId,
                        cinemaTime = cinemaTime,
                        cinemaName = cinemaName,
                        cinemaId = cinemaId,

                        seatsData = seatsData,
                        rowData = rowData,
                        totalPrice = mTotalPayAmount?.toInt(),

                        snacks = snackJsonString

                    ), REQUEST_RESULT
                )
            }


        }

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
            ticketAmount?.let { mTotalSnackAmt.plus(it) })
        tvSubTotal.text = String.format(getString(R.string.txt_sub_total_pay_amount),
            ticketAmount?.let { mTotalSnackAmt.plus(it) })

        mTotalPayAmount =
            ticketAmount?.let { mTotalSnackAmt.plus(it) } // to add another page as a param


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
            ticketAmount?.let { mTotalSnackAmt.plus(it) })
        tvSubTotal.text = String.format(getString(R.string.txt_sub_total_pay_amount),
            ticketAmount?.let { mTotalSnackAmt.plus(it) })

        mTotalPayAmount =
            ticketAmount?.let { mTotalSnackAmt.plus(it) } // to add another page as a param
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_RESULT) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                // success
                var resultData = 0
                data.apply {
                    resultData = getIntExtra(PaymentConfirmActivity.RESULT_DATA, 0)

                }

                if (resultData == VoucherActivity.VOUCHER_PAGE_SUCCESS) {
                    setResult(RESULT_OK, Intent().apply {
                        putExtra(RESULT_DATA, resultData)
                    })
                    finish()
                }

            } else {
                // fail
            }
        }
    }
}