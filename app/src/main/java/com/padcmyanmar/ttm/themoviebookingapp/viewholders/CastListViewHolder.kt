package com.padcmyanmar.ttm.themoviebookingapp.viewholders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.utils.BASE_URL
import com.padcmyanmar.ttm.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.view_holder_cast.view.*
import kotlinx.android.synthetic.main.view_holder_chip_genre.view.*

class CastListViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {


    private var mActorVO: ActorVO? = null

    fun bindData(mActorVO: ActorVO){

        this.mActorVO = mActorVO


        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${mActorVO.profilePath}")
            .placeholder(R.drawable.pic_profile)
            .into(itemView.profile_image)
    }
}