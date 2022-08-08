package com.padcmyanmar.ttm.themoviebookingapp.activities


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.CardListCarouselAdapter
import com.padcmyanmar.ttm.themoviebookingapp.delegate.PaymentCardDelegate
import kotlinx.android.synthetic.main.activity_payment_confirm.*


class PaymentConfirmActivity : AppCompatActivity() , PaymentCardDelegate{

    lateinit var cardListCarouselAdapter: CardListCarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_confirm)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setUpCarouselView()
        setUpClickListener()


    }

    private fun setUpCarouselView() {
        cardListCarouselAdapter = CardListCarouselAdapter(this)
        carouselRecyclerview.adapter = cardListCarouselAdapter
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

}

    override fun onTapPaymentCard() {
       Toast.makeText(this,"payment card click event",Toast.LENGTH_SHORT).show()
    }

}