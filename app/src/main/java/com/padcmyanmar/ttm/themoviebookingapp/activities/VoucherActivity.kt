package com.padcmyanmar.ttm.themoviebookingapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.themoviebookingapp.R
import kotlinx.android.synthetic.main.activity_voucher.*

class VoucherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        btnClose.setOnClickListener {
            finish()
        }
    }
}