package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.themoviebookingapp.R
import kotlinx.android.synthetic.main.activity_add_card.*

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpClickListener()
    }

    private fun setUpClickListener() {
        btnCardConfirm.setOnClickListener {
            finish()

        }
        btnBack.setOnClickListener { 
            onBackPressed()
        }
    }
}