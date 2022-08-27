package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater

import android.view.ViewGroup
import com.github.islamkhsh.CardSliderAdapter
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CardsVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.PaymentCardDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.CardListCarouselViewHolder

class CardListCarouselAdapter():  CardSliderAdapter<CardListCarouselViewHolder>() {
//private val onDelegate: PaymentCardDelegate
    var mCardsVOList: List<CardsVO> = listOf()


    override fun getItemCount(): Int {
        return mCardsVOList.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListCarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_card_list, parent, false)
        return CardListCarouselViewHolder(view)
    }

    override fun bindVH(holder: CardListCarouselViewHolder, position: Int) {
        if(mCardsVOList.isNotEmpty())
        {
            holder.bindData(mCardsVOList[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cardsVOList: List<CardsVO>){
        this.mCardsVOList = cardsVOList
        notifyDataSetChanged()
    }


}
