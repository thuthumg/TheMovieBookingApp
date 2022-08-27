package com.padcmyanmar.ttm.themoviebookingapp.activities

//import com.padcmyanmar.ttm.themoviebookingapp.dummy.DUMMY_SEATS

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.MovieSeatAdapter
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.MovieSeatVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.SeatingPlanDelegate
import com.padcmyanmar.ttm.themoviebookingapp.utils.DATE_FORMAT_YEAR_MONTH_DAY
import kotlinx.android.synthetic.main.activity_seating_plan.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class SeatingPlanActivity : AppCompatActivity(), SeatingPlanDelegate {

    companion object {

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
            cinemaId: Int?

        ): Intent {
            val intent = Intent(context, SeatingPlanActivity::class.java)

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

            return intent
        }
    }

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    private val mMovieSeatAdapter: MovieSeatAdapter = MovieSeatAdapter(this)
    private var movieId: Int? = null
    private var movieBookingDateYMDFormat: String? = null
    private var cinemaTimeSlotId: Int? = null
    private var movieName: String? = null
    private var cinemaTime: String? = null
    private var cinemaName: String? = null
    private var movieBookingDate: String? = null
    private var movieBookingDay: String? = null

    private var seatsData: String? = null
    private var rowData: String? = null
    private var cinemaId: Int? = null
    private var moviePic: String? = null

    var mMovieSeatVOs: List<MovieSeatVO> = listOf()

    var tvTicketCount: Int = 0
    var ticketAmt: Double = 0.00

    private val movieSeatSelectedData = mutableListOf<String>()
    private val movieSeatRowSelectedData = mutableListOf<String>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seating_plan)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        getIntentParamAndRequestData()
        setUpMovieBookingData()
        setUpSeatingPlan()
        setUpClickListener()

    }

    private fun getIntentParamAndRequestData() {

        //Booking date data param
        movieBookingDateYMDFormat =
            intent?.getStringExtra(MOVIE_BOOKING_DATE_YMD_FORMAT) // 2022-08-26
        movieBookingDate = intent?.getStringExtra(MOVIE_BOOKING_DATE)
        movieBookingDay = intent?.getStringExtra(MOVIE_BOOKING_DAY)

        //movie data param
        movieId = intent?.getIntExtra(MOVIE_ID, 0)
        movieName = intent?.getStringExtra(MOVIE_NAME)
        moviePic = intent?.getStringExtra(MOVIE_PIC)

        //cinema data param
        cinemaTimeSlotId = intent?.getIntExtra(CINEMA_TIMESLOT_ID, 0)
        cinemaTime = intent?.getStringExtra(CINEMA_TIME) // 12:00PM
        cinemaName = intent?.getStringExtra(CINEMA_NAME)
        cinemaId = intent?.getIntExtra(CINEMA_ID, 0)



        Log.d(
            "SeatingPlan", "intent param = $movieId \n" +
                    "$movieBookingDateYMDFormat \n" +
                    "$cinemaTimeSlotId \n" +
                    "$movieName \n" +
                    "$cinemaTime \n" +
                    "$cinemaName \n" +
                    "$movieBookingDate \n" +
                    "$movieBookingDay \n" +
                    "$cinemaId \n" +
                    "$moviePic"
        )

        //call API to bind data
        requestData(
            cinemaTimeSlotId = cinemaTimeSlotId,
            movieBookingDateYMDFormat = movieBookingDateYMDFormat
        )


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpMovieBookingData() {

        val dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT_YEAR_MONTH_DAY)
        val bookingDate = LocalDate.parse(movieBookingDateYMDFormat, dateFormat)

        val dayOfTheWeek: String = bookingDate.dayOfWeek.toString().substring(0, 1).toUpperCase() +
                bookingDate.dayOfWeek.toString().substring(1).toLowerCase()

        val day: String = bookingDate.dayOfMonth.toString()
        val monthString: String = bookingDate.month.toString().substring(0, 1).toUpperCase() +
                bookingDate.month.toString().substring(1).toLowerCase()

        //auto select bind data
        movieBookingDate = dayOfTheWeek
        movieBookingDay = day


//        val sDate1 = movieBookingDate
//        val date1 = SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY).parse(sDate1)
//
//        val date: Date = date1
//        val dateFormatForWeek: DateFormat = SimpleDateFormat("EEEE")
//        val dayOfTheWeek: String = dateFormatForWeek.format(date)
//
//        val dateFormatForDay: DateFormat = SimpleDateFormat("dd")
//        val day: String = dateFormatForDay.format(date)
//
//        val dateFormatForMonth: DateFormat = SimpleDateFormat("MMM")
//        val monthString: String = dateFormatForMonth.format(date)

        tvMovieTitle.text = movieName
        btnBuyTicket.text = "Buy Ticket for \$ $ticketAmt"
        tvBookingDateTime.text = "$dayOfTheWeek, $day $monthString , $cinemaTime"
        tvTicket.text = "$tvTicketCount"
        tvCinemaName.text = cinemaName
    }

    private fun setUpSeatingPlan() {
        rvSeatingPlan.adapter = mMovieSeatAdapter
        rvSeatingPlan.layoutManager = GridLayoutManager(applicationContext, 14)
        // mMovieSeatAdapter.setNewData(DUMMY_SEATS)
    }

    private fun setUpClickListener() {
        btnBuyTicket.setOnClickListener {
            if (ticketAmt == 0.00) {
                showError("Please select the seat to buy ticket.")
            } else {
                seatsData = tvSeats.text.toString()
                Log.d(
                    "SeatingPlan",
                    "check seat name param = ${tvSeats.text.toString()} /// $seatsData" +
                            "/// $movieBookingDate - $movieBookingDay"
                )

                startActivity(
                    SnackActivity.newIntent(
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
                        ticketAmt = ticketAmt



                    )
                )

            }
        }

        btnSeatingPlanBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun requestData(cinemaTimeSlotId: Int?, movieBookingDateYMDFormat: String?) {

        mMovieBookingModel.getSeatingPlan(
            cinemaDayTimeslotId = cinemaTimeSlotId ?: 0,
            bookingDate = movieBookingDateYMDFormat ?: "",
            onSuccess = { movieSeatVOs ->
                mMovieSeatVOs = movieSeatVOs
                mMovieSeatAdapter.setNewData(mMovieSeatVOs)
            },
            onFailure = {
                showError(it)
            })

    }

    private fun showError(it: String) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    override fun onTapSeating(
        movieSeatId: Int?,
        movieSeatSymbol: String?,
        movieSeatSelected: Boolean?,
        movieSeatPrice: Int?,
        movieSeatAvailable: Boolean?,
        positionParam: Int?,
        movieSeatCount: Int?
    ) {
        //for multiple selection
        mMovieSeatVOs.forEach {

            if (movieSeatId == it.id && movieSeatSymbol == it.symbol && it.isMovieSeatAvailable()) {
                if (it.isSelected) {
                    it.isSelected = false

                    val iterator = movieSeatSelectedData.iterator()
                    outerLoop@ while (iterator.hasNext()) {
                        val item = iterator.next()
                        if (item == it.seat_name) {
                            tvTicketCount -= 1
                            iterator.remove()
                            ticketAmt -= (it.price?.times(100.0))?.roundToInt()
                                ?.div(100.0) ?: 0.00
                            break@outerLoop
                        }
                    }

                } else {
                    it.isSelected = true

                    movieSeatSelectedData.add(it.seat_name ?: "")
                    movieSeatRowSelectedData.add(it.symbol ?: "")
                    tvTicketCount += 1
                    it.price?.let { priceData ->
                        ticketAmt += (priceData.times(100.0)).roundToInt().div(100.0)
                    }

                }


            }
            //else{
//               it.isSelected = false
//           }

        }

        tvSeats.text = movieSeatSelectedData?.joinToString(", ") { it } ?: ""
        rowData = movieSeatRowSelectedData?.joinToString(",") { it } ?: ""

        tvTicket.text = "$tvTicketCount"
        btnBuyTicket.text = String.format(getString(R.string.txt_buy_ticket), ticketAmt)

        mMovieSeatAdapter.setNewData(mMovieSeatVOs)

    }
}