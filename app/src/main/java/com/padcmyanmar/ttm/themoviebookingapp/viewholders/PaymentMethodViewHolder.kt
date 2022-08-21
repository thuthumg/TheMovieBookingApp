package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.PaymentMethodVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.PaymentMethodDelegate
import kotlinx.android.synthetic.main.view_holder_payment_method.view.*
import kotlinx.android.synthetic.main.view_holder_snack.view.*

class PaymentMethodViewHolder(itemView: View,private val onDelegate: PaymentMethodDelegate) : RecyclerView.ViewHolder(itemView) {
    var mPaymentMethodVO:PaymentMethodVO? = null

    init {
        itemView.setOnClickListener {
            onDelegate.onTapPaymentMethod(mPaymentMethodVO)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun bindData(paymentMethodVO: PaymentMethodVO)
    {
        mPaymentMethodVO = paymentMethodVO
        itemView.tvPaymentTitle.text = paymentMethodVO.name
        itemView.tvPaymentDescription.text = paymentMethodVO.description

        if(paymentMethodVO.isSelected)
        {
            itemView.tvPaymentTitle.setTextColor(itemView.context.getColor(R.color.colorPrimary))
            itemView.tvPaymentDescription.setTextColor(itemView.context.getColor(R.color.colorPrimary))
            itemView.ivCreditCard.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_selected_credit_card))
        }else{
            itemView.tvPaymentTitle.setTextColor(itemView.context.getColor(R.color.blackLight4))
            itemView.tvPaymentDescription.setTextColor(itemView.context.getColor(R.color.whiteDark9))
            itemView.ivCreditCard.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_default_credit_card))

        }
    }
}