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

        private const val MOVIE_ID = "MOVIE_ID"
        private const val MOVIE_DATE = "MOVIE_DATE"
        private const val TIMESLOT_ID = "TIMESLOT_ID"
        private const val MOVIE_NAME = "MOVIE_NAME"
        private const val TIMESLOT_VALUE = "TIMESLOT_VALUE"
        private const val CINEMA_NAME = "CINEMA_NAME"
        private const val BOOKING_DATE = "BOOKING_DATE"
        private const val BOOKING_DAY = "BOOKING_DAY"
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
            cinemaIdParam: Int?,
            moviePic:String?
        ): Intent {
            val intent = Intent(context, SeatingPlanActivity::class.java)
            intent.putExtra(MOVIE_ID, movieId)
            intent.putExtra(MOVIE_DATE, movieDateParam)
            intent.putExtra(TIMESLOT_ID, movieTimeSlotId)
            intent.putExtra(MOVIE_NAME, movieName)
            intent.putExtra(TIMESLOT_VALUE, movieTimeSlotValue)
            intent.putExtra(CINEMA_NAME, cinemaName)
            intent.putExtra(BOOKING_DATE, intentParamMovieBookingDate)
            intent.putExtra(BOOKING_DAY, intentParamMovieBookingDay)
            intent.putExtra(CINEMA_ID,cinemaIdParam)
            intent.putExtra(MOVIE_PIC,moviePic)
            return intent
        }
    }

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    private val mMovieSeatAdapter: MovieSeatAdapter = MovieSeatAdapter(this)
    private var movieId: Int? = null
    private var movieBookingDate: String? = null
    private var movieBookingTimeSlotId: Int? = null
    private var movieName: String? = null
    private var timeslotValue: String? = null
    private var cinemaName: String? = null
    private var intentParamBookingDate: String? = null
    private var intentParamBookingDay: String? = null
    private var intentParamSeats: String? = null
    private var intentParamRow: String? = null
    private var cinemaId: Int? = null
    private var moviePic:String? = null

    var mMovieSeatVOs: List<MovieSeatVO> = listOf()

    var tvSelectedSeats: String = ""
    var tvTicketCount: Int = 0
    var tvBuyTicketAmt: Double = 0.00

    //var movieSeatSelectedData:ArrayList<String> = arrayListOf()
    val movieSeatSelectedData = mutableListOf<String>()
    val movieSeatRowSelectedData = mutableListOf<String>()

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


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpMovieBookingData() {

        val dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT_YEAR_MONTH_DAY)
        val bookingDate = LocalDate.parse(movieBookingDate, dateFormat)

        val dayOfTheWeek: String = bookingDate.dayOfWeek.toString().substring(0, 1).toUpperCase() +
                bookingDate.dayOfWeek.toString().substring(1).toLowerCase()

        val day: String = bookingDate.dayOfMonth.toString()
        val monthString: String = bookingDate.month.toString().substring(0, 1).toUpperCase() +
                bookingDate.month.toString().substring(1).toLowerCase()

        //auto select bind data
        intentParamBookingDate = dayOfTheWeek
        intentParamBookingDay = day


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
        btnBuyTicket.text = "Buy Ticket for \$ $tvBuyTicketAmt"
        tvBookingDateTime.text = "$dayOfTheWeek, $day $monthString , $timeslotValue"
        tvTicket.text = "$tvTicketCount"
        tvCinemaName.text = cinemaName
    }

    private fun getIntentParamAndRequestData() {
        movieId = intent?.getIntExtra(MOVIE_ID, 0)
        movieBookingDate = intent?.getStringExtra(MOVIE_DATE)
        movieBookingTimeSlotId = intent?.getIntExtra(TIMESLOT_ID, 0)
        movieName = intent?.getStringExtra(MOVIE_NAME)
        timeslotValue = intent?.getStringExtra(TIMESLOT_VALUE)
        cinemaName = intent?.getStringExtra(CINEMA_NAME)
        intentParamBookingDate = intent?.getStringExtra(BOOKING_DATE)
        intentParamBookingDay = intent?.getStringExtra(BOOKING_DAY)
        cinemaId = intent?.getIntExtra(CINEMA_ID,0)
        moviePic = intent?.getStringExtra(MOVIE_PIC)

        //call API to bind data
        requestData(
            movieBookingTimeSlotId,
            movieBookingDate
        )


    }

    private fun requestData(movieBookingTimeSlotId: Int?, movieBookingDate: String?) {

        mMovieBookingModel.getSeatingPlan(
            cinemaDayTimeslotId = movieBookingTimeSlotId ?: 0,
            bookingDate = movieBookingDate ?: "",
            onSuccess = { movieSeatVOs ->
                mMovieSeatVOs = movieSeatVOs
                mMovieSeatAdapter.setNewData(mMovieSeatVOs)
            },
            onFailure = {
                showError(it)
            })

    }

    private fun setUpClickListener() {
        btnBuyTicket.setOnClickListener {
            if (tvBuyTicketAmt == 0.00) {
                showError("Please select the seat to buy ticket.")
            } else {
                intentParamSeats = tvSeats.text.toString()
                Log.d("SeatingPlan","check seat name param = ${tvSeats.text.toString()} /// $intentParamSeats" +
                        "/// $intentParamBookingDate - $intentParamBookingDay")

                startActivity(
                    SnackActivity.newIntent(
                        this,
                        movieId = movieId,
                        movieDateParam = movieBookingDate,
                        movieTimeSlotId = movieBookingTimeSlotId,
                        movieName = movieName,
                        movieTimeSlotValue = timeslotValue,
                        cinemaName = cinemaName,
                        intentParamMovieBookingDate = intentParamBookingDate,
                        intentParamMovieBookingDay = intentParamBookingDay,
                        intentParamSeats = intentParamSeats,
                        intentParamRow = intentParamRow,
                        toBuyTicketAmt = tvBuyTicketAmt,
                        cinemaId = cinemaId,
                        moviePic = moviePic

                    )
                )

            }
        }

        btnSeatingPlanBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpSeatingPlan() {
        rvSeatingPlan.adapter = mMovieSeatAdapter
        rvSeatingPlan.layoutManager = GridLayoutManager(applicationContext, 14)
        // mMovieSeatAdapter.setNewData(DUMMY_SEATS)
    }

    private fun showError(it: String) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    override fun onTapSeating(
        movieSeatId:Int?,
        movieSeatSymbol:String?,
        movieSeatSelected:Boolean?,
        movieSeatPrice:Int?,
        movieSeatAvailable:Boolean?,
        positionParam: Int?,
        movieSeatCount: Int?
    ) {

       mMovieSeatVOs.forEach {

           if(movieSeatId == it.id && movieSeatSymbol == it.symbol && it.isMovieSeatAvailable())
           {
               if(it.isSelected)
               {
                   it.isSelected = false

                   val iterator = movieSeatSelectedData.iterator()
                   outerLoop@ while (iterator.hasNext()) {
                       val item = iterator.next()
                       if (item == it.seat_name) {
                           tvTicketCount -= 1
                           iterator.remove()
                           tvBuyTicketAmt -= (it.price?.times(100.0))?.roundToInt()
                               ?.div(100.0) ?: 0.00
                           break@outerLoop
                       }
                   }

               }else{
                   it.isSelected = true

                   movieSeatSelectedData.add(it.seat_name ?: "")
                   movieSeatRowSelectedData.add(it.symbol ?: "")
                   tvTicketCount += 1
                   it.price?.let {priceData->
                       tvBuyTicketAmt += (priceData.times(100.0)).roundToInt().div(100.0)
                   }

               }



           }else{
               it.isSelected = false
           }

       }

        tvSeats.text = movieSeatSelectedData?.joinToString(", ") { it } ?: ""
        intentParamRow = movieSeatRowSelectedData?.joinToString(",") { it } ?: ""

        tvTicket.text = "$tvTicketCount"

        // btnBuyTicket.text = "Buy Ticket for \$ $tvBuyTicketAmt"
        btnBuyTicket.text = String.format(getString(R.string.txt_buy_ticket), tvBuyTicketAmt)

        mMovieSeatAdapter.setNewData(mMovieSeatVOs)

    }
}