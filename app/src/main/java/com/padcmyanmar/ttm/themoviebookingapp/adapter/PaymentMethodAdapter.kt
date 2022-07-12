package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.PaymentMethodViewHolder

class PaymentMethodAdapter : RecyclerView.Adapter<PaymentMethodViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_payment_method,parent,false)

        return PaymentMethodViewHolder(view)

    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {

        return 4
    }
}