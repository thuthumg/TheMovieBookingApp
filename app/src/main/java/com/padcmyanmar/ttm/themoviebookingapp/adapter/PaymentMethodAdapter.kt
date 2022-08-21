package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.PaymentMethodVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.PaymentMethodDelegate
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.PaymentMethodViewHolder

class PaymentMethodAdapter(private val onDelegate: PaymentMethodDelegate) : RecyclerView.Adapter<PaymentMethodViewHolder>(){

    var mPaymentMethodList:List<PaymentMethodVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_payment_method,parent,false)

        return PaymentMethodViewHolder(view,onDelegate)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {

        if (mPaymentMethodList.isNotEmpty())
        {
            holder.bindData(mPaymentMethodList[position])
        }
    }

    override fun getItemCount(): Int {

        return mPaymentMethodList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(paymentMethodList:List<PaymentMethodVO>){
        this.mPaymentMethodList = paymentMethodList
        notifyDataSetChanged()
    }
}