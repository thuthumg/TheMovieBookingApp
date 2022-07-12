package com.padcmyanmar.ttm.themoviebookingapp.adapter
import alirezat775.lib.carouselview.CarouselAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.themoviebookingapp.R
import com.padcmyanmar.ttm.themoviebookingapp.viewholders.CardListCarouselViewHolder

/*class CardListCarouselAdapter: RecyclerView.Adapter<CardListCarouselViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListCarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_card_list,parent,false)

        return CardListCarouselViewHolder(view)

    }

    override fun onBindViewHolder(holder: CardListCarouselViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

        return 10

    }
}*/

class CardListCarouselAdapter: CarouselAdapter() {
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_card_list,parent,false)

        return CardListCarouselViewHolder(view)
    }

    inner class CardListCarouselViewHolder(var itemView: View)   : CarouselAdapter.CarouselViewHolder(itemView) {


    }
}

