package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import com.github.islamkhsh.CardSliderAdapter
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.delegate.PaymentCardDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.CardListCarouselViewHolder

class CardListCarouselAdapter(private val onDelegate: PaymentCardDelegate):  CardSliderAdapter<CardListCarouselViewHolder>() {

    override fun getItemCount() = 10

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListCarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_card_list, parent, false)
        return CardListCarouselViewHolder(view,onDelegate)
    }

    override fun bindVH(holder: CardListCarouselViewHolder, position: Int) {

    }


}
