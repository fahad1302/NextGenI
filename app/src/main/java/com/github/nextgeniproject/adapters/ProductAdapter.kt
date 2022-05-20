package com.github.nextgeniproject.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.nextgeniproject.R
import com.github.nextgeniproject.commons.Constants
import com.github.nextgeniproject.models.ProductModel
import com.github.nextgeniproject.ui.DetailsActivity
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var productList: List<ProductModel.ProductData>

    init {
        productList = ArrayList()
    }

    fun addData(arrList: List<ProductModel.ProductData>) {
        this.productList = arrList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(productData: ProductModel.ProductData) {
            populateData(productData)
        }

        fun populateData(productData: ProductModel.ProductData) {
            itemView.name.text = productData.name
            itemView.vendor_name.text = productData.vendorName
            itemView.price.text = "$" + productData.finalPriceSale.toString()
            setImageThumbnail(productData.thumbnailUrl!!)
            cardViewListener(productData.productId.toString())
        }

        fun setImageThumbnail(thumbnailUrl: String) {
            Glide.with(itemView.context)
                    .load(thumbnailUrl).centerCrop().apply(RequestOptions().override(150, 150))
                    .into(itemView.thumbnail)
        }

        fun cardViewListener(product_id: String) {
            itemView.card_view.setOnClickListener(View.OnClickListener {
                val intent = Intent(itemView.context, DetailsActivity::class.java)
                intent.putExtra(Constants.PRODUCT_ID, product_id)
                itemView.context.startActivity(intent)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_product, parent,
                            false
                    )
            )

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(productList.get(pos))
    }

}