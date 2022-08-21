package com.padcmyanmar.ttm.themoviebookingapp.delegate

import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CardsVO

interface PaymentCardDelegate {
    fun onTapPaymentCard(cardsVO: CardsVO?)
}