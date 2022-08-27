package com.padcmyanmar.ttm.themoviebookingapp.viewholders


import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.CardsVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.PaymentCardDelegate
import kotlinx.android.synthetic.main.view_holder_card_list.view.*

class CardListCarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mCardsVO: CardsVO? = null

//    init {
//        itemView.setOnClickListener {
//            onDelegate.onTapPaymentCard(mCardsVO)
//        }
//
//
//
//
//
//    }

    fun bindData(cardsVO: CardsVO) {

        mCardsVO = cardsVO
        var strCardNumbers = cardsVO.cardNumber?.chunked(4)?.joinToString(separator = "\t\t\t")
        var strFourDigitArrayCardNumbers = strCardNumbers?.split(" ")?.toTypedArray()
      //  itemView.tvCardType.text = cardsVO.cardType
        itemView.tvCardHolderName.text = cardsVO.cardHolder
        itemView.tvExpiresDate.text = cardsVO.expirationDate
        itemView.tvPaymentCardNumberFirstPart.text = strCardNumbers
       // itemView.tvPaymentCardNumberFirstPart.text = strFourDigitArrayCardNumbers?.getOrNull(0) ?: "****"
       // itemView.tvPaymentCardNumberSecondPart.text = strFourDigitArrayCardNumbers?.getOrNull(1) ?: "****"
      //  itemView.tvPaymentCardNumberThirdPart.text = strFourDigitArrayCardNumbers?.getOrNull(2) ?: "****"
      //  itemView.tvPaymentCardNumberFourthPart.text = strFourDigitArrayCardNumbers?.getOrNull(3) ?: "****"

    }
}
