package com.padcmyanmar.ttm.themoviebookingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.ComboSetViewHolder

class SnackAdapter : RecyclerView.Adapter<ComboSetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComboSetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_snack,parent,false)
        return ComboSetViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComboSetViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {

        return 5

    }
}