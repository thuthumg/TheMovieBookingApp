package com.padcmyanmar.ttm.themoviebookingapp.activities


import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselLazyLoadListener
import alirezat775.lib.carouselview.CarouselModel
import alirezat775.lib.carouselview.CarouselView
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL


import com.padcmyanmar.ttm.themoviebookingapp.R

import com.padcmyanmar.ttm.themoviebookingapp.adapter.CardListCarouselAdapter
import kotlinx.android.synthetic.main.activity_payment_confirm.*


class PaymentConfirmActivity : AppCompatActivity() {

    lateinit var cardListCarouselAdapter: CardListCarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_confirm)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setUpCarouselView()
        setUpClickListener()


    }

    private fun setUpCarouselView() {
        cardListCarouselAdapter = CardListCarouselAdapter()

        val carousel = Carousel(this,carouselRecyclerview,cardListCarouselAdapter)
        carousel.setOrientation(HORIZONTAL,
            false)
      //  carousel.autoScroll(true,5000,true)
        carousel.scaleView(true)


        val  carouselModel = CarouselModel()

        carousel.add(item = carouselModel)
        carousel.add(item = carouselModel)
        carousel.add(item = carouselModel)
        carousel.add(item = carouselModel)
        carousel.add(item = carouselModel)
        carousel.add(item = carouselModel)
        carousel.add(item = carouselModel)
        carousel.add(item = carouselModel)
        carousel.add(item = carouselModel)
//        carouselRecyclerview.adapter = cardListCarouselAdapter
//        carouselRecyclerview.apply {
//            set3DItem(false)
//            setAlpha(true)
//            setInfinite(true)
//            setIntervalRatio(0.77f)

        }






    private fun setUpClickListener() {
        btnConfirm.setOnClickListener {
            startActivity(Intent(this,VoucherActivity::class.java))
        }
        btnBack.setOnClickListener {
            onBackPressed()
        }

        rlBtnAddNewCard.setOnClickListener {
            startActivity(Intent(this, AddCardActivity::class.java))
        }

//        carouselRecyclerview.setItemSelectListener(object : CarouselLayoutManager.OnSelected {
//            override fun onItemSelected(position: Int) {
//                //Cente item
//                Toast.makeText(applicationContext,"Selected Item position = $position", Toast.LENGTH_SHORT).show()
//
//            }
//        })

}

}