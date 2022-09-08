package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.BookingDateListAdapter
import com.padcmyanmar.ttm.themoviebookingapp.adapter.BookingTimeObjectAdapter
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.BookingDate
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CinemaDayTimeslotVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.AvailableTicketDelegate
import com.padcmyanmar.ttm.themoviebookingapp.delegate.BookingDateDelegate
import com.padcmyanmar.ttm.themoviebookingapp.utils.DATE_FORMAT
import com.padcmyanmar.ttm.themoviebookingapp.utils.TWO_WEEKS
import kotlinx.android.synthetic.main.activity_ticket.*
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class BookingDateTimeActivity : AppCompatActivity(), AvailableTicketDelegate,
    BookingDateDelegate {

    companion object {

        private const val MOVIE_ID = "MOVIE_ID"
        private const val MOVIE_NAME = "MOVIE_NAME"
        private const val MOVIE_PIC = "MOVIE_PIC"

        fun newIntent(
            context: Context,
            movieId: Int,
            movieTitle: String,
            moviePic: String
        ): Intent {
            val intent = Intent(context, BookingDateTimeActivity::class.java)
            intent.putExtra(MOVIE_ID, movieId)
            intent.putExtra(MOVIE_NAME, movieTitle)
            intent.putExtra(MOVIE_PIC, moviePic)
            return intent
        }
    }

    lateinit var mBookingDateListAdapter: BookingDateListAdapter
    lateinit var mBookingTimeObjectAdapter: BookingTimeObjectAdapter

    //Booking Date Data
    private var mBookingDateData: ArrayList<BookingDate>? = arrayListOf()
    private var movieIdParam: Int? = null
    private var cinemaIdParam: Int? = null
    private var movieNameParam: String? = ""
    private var moviePic: String? = ""
    private var mCinemaDayTimeslotVOs: List<CinemaDayTimeslotVO> = listOf()


    @SuppressLint("SimpleDateFormat")
    var s = SimpleDateFormat(DATE_FORMAT)
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private var cinemaTimeSlotId: Int? = null
    private var cinemaTime: String? = null
    private var cinemaName: String? = null
    private var movieBookingDateYMDFormat: String? = null

    private var movieBookingDate: String? = null
    private var movieBookingDay: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        getMovieDetailIntentParam()
        setUpAvailableTicketItemAdapter()
        getMovieBookingDate()
        setUpClickListener()


    }

    //get param from Movie Detail page
    private fun getMovieDetailIntentParam() {
        val movieId = intent?.getIntExtra(MOVIE_ID, 0)
        val movieName = intent?.getStringExtra(MOVIE_NAME)
        moviePic = intent?.getStringExtra(MOVIE_PIC)
        movieId?.let {
            movieIdParam = movieId
        }
        movieNameParam = movieName
        pbTicketBookingTimeLoading.visibility = View.VISIBLE
    }

    //get Movie Date Setup
    private fun getMovieBookingDate() {
        mBookingDateData = calculateTwoWeeks()
        //data setup at UI
        setUpDateListAdapter()
        //auto select at first time
        mBookingDateData?.firstOrNull()?.isSelected = true
        mBookingDateData?.firstOrNull()?.bookingFormatDate.let { bookingDate ->
            movieBookingDateYMDFormat = bookingDate
            getMovieTimeSlotsByBookingDate(movieIdParam, bookingDate) // get movie time call API
        }

    }

    // get movie time data from call API and bind data at UI
    private fun getMovieTimeSlotsByBookingDate(movieIdParam: Int?, bookingDate: String?) {

        //network
        bookingDate?.let {
            mMovieBookingModel.getCinemaDayTimeslot(
                movieId = movieIdParam.toString(),
                dateParam = it,
                onSuccess = { cinemaDayTimeslotVOList ->
                    pbTicketBookingTimeLoading.visibility = View.GONE
                    mCinemaDayTimeslotVOs = cinemaDayTimeslotVOList
                    mBookingTimeObjectAdapter.setData(cinemaDayTimeslotVOList)
                },
                onFailure = { msgString ->
                    pbTicketBookingTimeLoading.visibility = View.GONE
                    showError(msgString)
                })
        }

    }

    @SuppressLint("SimpleDateFormat")
    private fun calculateTwoWeeks(): ArrayList<BookingDate> {
        val twoWeeksDateData: ArrayList<BookingDate> = arrayListOf()
        var mBookingDate: BookingDate? = null
        val cal: Calendar = Calendar.getInstance()
        s = SimpleDateFormat(DATE_FORMAT)
        val dateStart: String = s.format(Date(cal.timeInMillis))

        // to get next two weeks
        val dateEnd: String = getCalculatedDate(DATE_FORMAT, TWO_WEEKS)

        //to get array date list
        val dates: List<Date> = getDates(dateStart, dateEnd)
        for (date in dates) {
            val dateItem: String = s.format(date)//111
            val strDateItem = dateItem.split(" ").toTypedArray()
            val strDate = strDateItem.getOrNull(0) // Mon , Tue , Wed, Thur, Fri
            val strYearMonthDay = strDateItem.getOrNull(1) // yyyy-MM-dd
            val strDayData = strDateItem.getOrNull(1)?.split("-")?.toTypedArray()
            strDayData?.let {
                mBookingDate = BookingDate(
                    bookingDate = strDate,
                    bookingDay = it.getOrNull(2),
                    bookingFormatDate = strYearMonthDay
                )
            }
            mBookingDate?.let { twoWeeksDateData.add(it) }
        }

        return twoWeeksDateData
    }

    private fun getDates(dateString1: String, dateString2: String): List<Date> {
        val dates = ArrayList<Date>()
        val df1: DateFormat = SimpleDateFormat(DATE_FORMAT)
        var date1: Date? = null
        var date2: Date? = null
        try {
            date1 = df1.parse(dateString1)
            date2 = df1.parse(dateString2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val cal1 = Calendar.getInstance()
        cal1.time = date1
        val cal2 = Calendar.getInstance()
        cal2.time = date2
        while (!cal1.after(cal2)) {
            dates.add(cal1.time)
            cal1.add(Calendar.DATE, 1)
        }
        return dates
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCalculatedDate(dateFormat: String, days: Int): String {
        val cal: Calendar = Calendar.getInstance()
        val s = SimpleDateFormat(dateFormat)
        cal.add(Calendar.DAY_OF_YEAR, days)
        return s.format(Date(cal.timeInMillis))

    }

    private fun setUpAvailableTicketItemAdapter() {
        mBookingTimeObjectAdapter = BookingTimeObjectAdapter(this)
        rvAvailableTicketItemList.adapter = mBookingTimeObjectAdapter
        rvAvailableTicketItemList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun setUpClickListener() {
        btnNext.setOnClickListener {

            if (cinemaTimeSlotId == null) {
                showError(getString(R.string.txt_booking_time_error))
            } else {

                startActivity(
                    SeatingPlanActivity.newIntent(
                        this,
                        movieBookingDateYMDFormat = movieBookingDateYMDFormat,
                        movieBookingDate = movieBookingDate,
                        movieBookingDay = movieBookingDay,

                        movieId = movieIdParam,
                        movieName = movieNameParam,
                        moviePic = moviePic,

                        cinemaTimeSlotId = cinemaTimeSlotId,
                        cinemaTime = cinemaTime,
                        cinemaName = cinemaName,
                        cinemaId = cinemaIdParam,

                        )
                )
            }

        }
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpDateListAdapter() {
        mBookingDateListAdapter = BookingDateListAdapter(this)
        rvDateList.adapter = mBookingDateListAdapter
        rvDateList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mBookingDateData?.let { mBookingDateListAdapter.setData(it) }
    }

    //movie time click function
    override fun onTapTimeSlotClick(timeslotId: Int?) {

        mCinemaDayTimeslotVOs.forEach {

            it.timeslots?.forEach { timeslotVO ->
                timeslotVO.isSelected = timeslotId == timeslotVO.cinemaDayTimeslotId

                if (timeslotId == timeslotVO.cinemaDayTimeslotId) {

                    cinemaTimeSlotId = timeslotVO.cinemaDayTimeslotId
                    cinemaTime = timeslotVO.startTime
                    cinemaName = it.cinema
                    cinemaIdParam = it.cinemaId

                }
            }


        }
        mBookingTimeObjectAdapter.setData(mCinemaDayTimeslotVOs)
    }

    //movie date click function
    override fun onTapDateDelegate(mBookingDate: BookingDate, isSelectedId: Int) {
        pbTicketBookingTimeLoading.visibility = View.VISIBLE
        cinemaTimeSlotId = null

        mBookingDateData?.forEach {
            it.isSelected = mBookingDate.bookingFormatDate == it.bookingFormatDate
            if (mBookingDate.bookingFormatDate == it.bookingFormatDate)
                movieBookingDateYMDFormat = it.bookingFormatDate
        }

        mBookingDateData?.let { mBookingDateListAdapter.setData(it) }

        movieBookingDate = mBookingDate.bookingDate
        movieBookingDay = mBookingDate.bookingDay

        mBookingDateData?.getOrNull(isSelectedId)?.bookingFormatDate?.let {
            getMovieTimeSlotsByBookingDate(movieIdParam, it)
        }


    }

    private fun showError(it: String) {

        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()


    }
}
