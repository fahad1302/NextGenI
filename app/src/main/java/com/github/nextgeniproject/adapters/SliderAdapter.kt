package com.github.nextgeniproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.github.nextgeniproject.R
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_image.view.*

class SliderAdapter() : SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {

    var imageList: List<String>

    init {
        this.imageList = ArrayList()
    }

    fun addData(arrList: List<String>) {
        this.imageList = arrList
    }

    class SliderAdapterViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        fun bind(imageUrl: String) {
            Glide.with(itemView.context)
                    .load(imageUrl).centerCrop().placeholder(R.mipmap.nextgeni_logo)
                    .into(itemView.myimage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup) =
            SliderAdapterViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_image, parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: SliderAdapterViewHolder, pos: Int) {
        holder.bind(imageList.get(pos))
    }

    override fun getCount(): Int {
        return imageList.size
    }
}
