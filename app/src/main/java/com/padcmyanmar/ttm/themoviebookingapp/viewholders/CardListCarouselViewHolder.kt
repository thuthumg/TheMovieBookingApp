package com.padcmyanmar.ttm.themoviebookingapp.viewholders


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.delegate.PaymentCardDelegate

class CardListCarouselViewHolder(itemView: View,private val onDelegate: PaymentCardDelegate) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            onDelegate.onTapPaymentCard()
        }

    }
}
