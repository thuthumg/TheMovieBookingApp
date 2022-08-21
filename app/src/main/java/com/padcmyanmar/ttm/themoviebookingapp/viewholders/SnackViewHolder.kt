package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.data.vos.SnackVO
import com.padcmyanmar.ttm.themoviebookingapp.delegate.SnackDelegate
import kotlinx.android.synthetic.main.view_holder_snack.view.*

class SnackViewHolder(itemView: View,private var onDelegate:SnackDelegate) : RecyclerView.ViewHolder(itemView) {

    var mSnackVO :SnackVO? = null

    init {
        itemView.btnMinus.setOnClickListener {
            onDelegate.onTapMinus(mSnackVO)
        }

        itemView.btnPlus.setOnClickListener {
            onDelegate.onTapPlus(mSnackVO)
        }
    }




    fun bindData(snackVO:SnackVO){

        mSnackVO = snackVO

        itemView.tvSnackTitle.text = snackVO.name
        itemView.tvSnackDescription.text = snackVO.description
        itemView.tvSnackPrice.text = String.format(itemView.context.getString(
            R.string.txt_snack_amount),snackVO.price.toString())
        itemView.tvQty.text = snackVO.quantity.toString()




    }
}

