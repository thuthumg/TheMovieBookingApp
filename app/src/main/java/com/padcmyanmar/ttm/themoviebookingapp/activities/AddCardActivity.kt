package com.padcmyanmar.ttm.themoviebookingapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModel
import com.padcmyanmar.ttm.themoviebookingapp.data.models.MovieBookingModelImpl
import com.padcmyanmar.ttm.themoviebookingapp.utils.FourDigitCardFormatWatcher
import kotlinx.android.synthetic.main.activity_add_card.*


class AddCardActivity : AppCompatActivity() {
    companion object {
        const val RESULT_DATA = "RESULT_DATA"
    }

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpCreditCardTextView()
        setUpClickListener()
    }

    private fun setUpCreditCardTextView() {
        tvCardNumberValue.addTextChangedListener(FourDigitCardFormatWatcher())
    }

    private fun setUpClickListener() {

        btnCardConfirm.setOnClickListener {

            val checkValidate = validation(
                cardNumber = tvCardNumberValue.text.toString(),
                cardHolder = tvCardHolderValue.text.toString(),
                expirationDate = tvExpirationDateValue.text.toString(),
                cvc = tvCVCValue.text.toString()
            )

            if (checkValidate) {
                var cardNumberArr = tvCardNumberValue.text.toString().split(".").toTypedArray()
                var cardNumberData: String = ""
                cardNumberArr.forEach {
                    cardNumberData += it
                }

                callCardCreateFunction(
                    cardNumber = cardNumberData,
                    cardHolder = tvCardHolderValue.text.toString(),
                    expirationDate = tvExpirationDateValue.text.toString(),
                    cvc = tvCVCValue.text.toString()
                )
            }


        }
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun validation(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String
    ): Boolean {
        if (cardNumber.isEmpty() || cardHolder.isEmpty() || expirationDate.isEmpty() || cvc.isEmpty()) {

            if (cardNumber.isEmpty())
                showToast("Please fill Card Number")
            else if (cardHolder.isEmpty())
                showToast("Please fill Card Holder")
            else if (expirationDate.isEmpty())
                showToast("Please fill Expiration Date")
            else if (cvc.isEmpty())
                showToast("Please fill CVC")
            return false
        } else {

            return true
        }
    }

    private fun callCardCreateFunction(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String
    ) {
        mMovieBookingModel.createCard(
            cardNumber,
            cardHolder,
            expirationDate,
            cvc,
            onSuccess = { cardNumber, message ->

                showToast(message)
                returnResult(cardNumber)

            },
            onFailure = {
                showToast(it)
            }
        )
    }

    private fun returnResult(cardNumber: String) {
        val data = Intent().apply {
            putExtra(RESULT_DATA, cardNumber)
        }
        setResult(RESULT_OK, data)
        finish()
    }

    private fun showToast(it: String) {

        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()


    }

}