package com.padcmyanmar.ttm.themoviebookingapp.delegate

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.PaymentMethodVO

interface PaymentMethodDelegate {

    fun onTapPaymentMethod(mPaymentMethodVO: PaymentMethodVO?)
}