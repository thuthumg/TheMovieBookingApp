package com.padcmyanmar.ttm.themoviebookingapp.activities


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.gson.Gson
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.CardListCarouselAdapter
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CardsVO
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.SnackVO
import com.padcmyanmar.ttm.themoviebookingapp.utils.jsonStringToObjectString
import kotlinx.android.synthetic.main.activity_payment_confirm.*


class PaymentConfirmActivity : AppCompatActivity() {//, PaymentCardDelegate

    companion object {
        private const val REQUEST_RESULT = 1

        //Booking date data param
        private const val MOVIE_BOOKING_DATE_YMD_FORMAT = "MOVIE_BOOKING_DATE_YMD_FORMAT"
        private const val MOVIE_BOOKING_DATE = "MOVIE_BOOKING_DATE "
        private const val MOVIE_BOOKING_DAY = "MOVIE_BOOKING_DAY"

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
        private const val TOTAL_PRICE = "TOTAL_PRICE"

        //snack data param
        private const val SNACK_LIST = "SNACK_LIST"


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
            totalPrice: Int?,

            snacks: String?

        ): Intent {

            val intent = Intent(context, PaymentConfirmActivity::class.java)

            //Booking date data param
            intent.putExtra(MOVIE_BOOKING_DATE_YMD_FORMAT, movieBookingDateYMDFormat)
            intent.putExtra(MOVIE_BOOKING_DATE, movieBookingDate)
            intent.putExtra(MOVIE_BOOKING_DAY, movieBookingDay)

            //movie data param
            intent.putExtra(MOVIE_ID, movieId)
            intent.putExtra(MOVIE_NAME, movieName)
            intent.putExtra(MOVIE_PIC, moviePic)

            //cinema data param
            intent.putExtra(CINEMA_TIMESLOT_ID, cinemaTimeSlotId)
            intent.putExtra(CINEMA_TIME, cinemaTime)
            intent.putExtra(CINEMA_NAME, cinemaName)
            intent.putExtra(CINEMA_ID, cinemaId)

            //seat data param and amount data param
            intent.putExtra(SEATS_DATA, seatsData)
            intent.putExtra(ROW_DATA, rowData)
            intent.putExtra(TOTAL_PRICE, totalPrice)

            //snack data param
            intent.putExtra(SNACK_LIST, snacks)

            return intent
        }
    }

    private lateinit var cardListCarouselAdapter: CardListCarouselAdapter

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    var mCardsVOList: List<CardsVO>? = listOf()
    var cardNumber: String? = null
    var g: Gson = Gson()

    private var movieName: String? = null
    private var cinemaTime: String? = null
    private var cinemaName: String? = null
    private var movieBookingDate: String? = null
    private var movieBookingDay: String? = null
    private var moviePic: String? = null

    private var cinemaTimeSlotId: Int? = null
    private var rowData: String? = null
    private var seatData: String? = null
    private var movieBookingDateYMDFormat: String? = null
    private var totalPrice: Int? = null
    private var movieId: Int? = null
    private var cinemaId: Int? = null
    private var snackListString: String? = null
    private var snackList: List<SnackVO>? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_confirm)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        getIntentParam()
        setUpTotalPriceData()
        setUpCarouselView()
        setUpClickListener()
        requestData(cardNumber)

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
        seatData = intent?.getStringExtra(SEATS_DATA)
        rowData = intent?.getStringExtra(ROW_DATA)
        totalPrice = intent?.getIntExtra(TOTAL_PRICE, 0)

        //snack data param
        snackListString = intent?.getStringExtra(SNACK_LIST)

        snackList = snackListString?.let { jsonStringToObjectString(it) }

        Log.d("PaymentConfirmActivity", "check array$snackList")


    }

    private fun setUpTotalPriceData() {
        tvPaymentAmount.text = "$ $totalPrice"
    }

    private fun setUpCarouselView() {
        cardListCarouselAdapter = CardListCarouselAdapter()
        carouselRecyclerview.adapter = cardListCarouselAdapter
    }

    private fun setUpClickListener() {
        btnConfirm.setOnClickListener {

            Log.d(
                "PaymentConfirm", "check check out function = $cinemaTimeSlotId" +
                        "$rowData // $seatData // $movieBookingDateYMDFormat // $totalPrice // $movieId // $cinemaId // " +
                        "${cardListCarouselAdapter.mCardsVOList.getOrNull(carouselRecyclerview.currentItem)?.id} //" +
                        "$snackList"
            )


            snackList?.let { it1 ->
                mMovieBookingModel.checkOut(
                    cinemaDayTimeSlotId = cinemaTimeSlotId,
                    rowData = rowData,
                    seatData = seatData,
                    movieBookingDateYMDFormat = movieBookingDateYMDFormat,
                    totalPrice = totalPrice,
                    movieId = movieId,
                    cinemaId = cinemaId,
                    cardId = cardListCarouselAdapter.mCardsVOList.
                    getOrNull(carouselRecyclerview.currentItem)?.id,
                    snackListString = it1,
                    onSuccess = { checkOutVO, message ->
                        val str: String = g.toJson(checkOutVO)
                        startActivity(
                            VoucherActivity.newIntent(
                                this,
                                checkOutVO = str,

                                movieBookingDate = movieBookingDate,
                                movieBookingDay = movieBookingDay,

                                cinemaTimeSlotId = cinemaTimeSlotId,
                                cinemaTime = cinemaTime,
                                cinemaName = cinemaName,

                                movieId = movieId,
                                movieName = movieName,
                                moviePic = moviePic,

                                cardId = cardListCarouselAdapter.mCardsVOList.getOrNull(
                                    carouselRecyclerview.currentItem
                                )?.id

                            )
                        )

                    },
                    onFailure = {
                        Log.d("VoucherActivity", "Fail ! = $it")
                        showError(it)
                    })
            }
        }
        btnBack.setOnClickListener {
            onBackPressed()
        }

        rlBtnAddNewCard.setOnClickListener {
            startActivityForResult(
                Intent(this, AddCardActivity::class.java),
                REQUEST_RESULT
            )
        }

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

    private fun requestData(cardNumber: String?) {
        mMovieBookingModel.getProfile(
            onSuccess = { userDataVO ->
                mCardsVOList = userDataVO.cards

                if (cardNumber == null) {
                    userDataVO.cards?.let { cardListCarouselAdapter.setData(it) }
                } else {
                    // setUpCarouselView()//
                    carouselRecyclerview.currentItem = 0
                    mCardsVOList = userDataVO.cards?.reversed()
                    mCardsVOList?.let { cardListCarouselAdapter.setData(it) }
                }

            },
            onFailure = {
                showError(it)
            }
        )

    }
}