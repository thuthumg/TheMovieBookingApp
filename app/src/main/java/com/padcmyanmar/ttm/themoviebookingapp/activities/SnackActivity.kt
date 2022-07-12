package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.adapter.SnackAdapter
import com.padcmyanmar.ttm.themoviebookingapp.adapter.PaymentMethodAdapter
import kotlinx.android.synthetic.main.activity_snack.*


class SnackActivity : AppCompatActivity() {

    lateinit var mComboSetAapter: SnackAdapter
    lateinit var mPaymentMethodAdapter: PaymentMethodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setUpComboSetAdapter()
        setUpPaymentMethodAdapter()
        setUpClickListener()
    }

    private fun setUpClickListener() {
        btnBack.setOnClickListener {
            onBackPressed()
        }
        btnPay.setOnClickListener {
            startActivity(Intent(this,PaymentConfirmActivity::class.java))
        }

    }

    private fun setUpPaymentMethodAdapter() {
        mPaymentMethodAdapter = PaymentMethodAdapter()
        rvPaymentMethod.adapter = mPaymentMethodAdapter
        rvPaymentMethod.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

    }

    private fun setUpComboSetAdapter() {

        mComboSetAapter = SnackAdapter()
        rvComboSet.adapter = mComboSetAapter
        rvComboSet.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    }
}