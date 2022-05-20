package com.github.nextgeniproject.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.github.nextgeniproject.R
import com.github.nextgeniproject.commons.Constants
import com.github.nextgeniproject.commons.MyApplication
import com.github.nextgeniproject.databinding.ActivityDetailsBinding
import com.github.nextgeniproject.models.ProductDetailsModel
import com.github.nextgeniproject.viewmodel.ProductViewModel
import com.github.nextgeniproject.adapters.SliderAdapter
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_description.view.*
import kotlinx.android.synthetic.main.item_product.view.card_view
import kotlinx.android.synthetic.main.item_product.view.name

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        val product_id: String = intent.getStringExtra(Constants.PRODUCT_ID)

        if (MyApplication.instance!!.isNetworkAvailable) {
            progressBar.visibility = View.VISIBLE
            ProductViewModel(product_id).getProductDetails().observe(this, object : Observer<ProductDetailsModel> {
                override fun onChanged(data: ProductDetailsModel?) {
                    binding.progressBar.setVisibility(View.GONE)
                    populateData(data)
                }
            })
        } else
            Toast.makeText(this, "No Network Available", Toast.LENGTH_LONG).show()
    }

    fun populateData(data: ProductDetailsModel?) {
        val product: ProductDetailsModel.Body = data!!.getBody()!!
        populateProductDetails(product)
        populatProductImages(product)
        populateProductInfo(product)
    }

    fun populateProductDetails(product: ProductDetailsModel.Body) {
        binding.name.text = product.name
        binding.brandName.text = product.brandName
        binding.price.text = "$" + product.finalPriceSale.toString()
    }

    fun populatProductImages(product: ProductDetailsModel.Body) {
        val imageAdapter: SliderAdapter = SliderAdapter()
        imageAdapter.addData(product.images!!)
        binding.slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        binding.slider.setSliderAdapter(imageAdapter)
        binding.slider.setScrollTimeInSec(3)
        binding.slider.setAutoCycle(true)
        binding.slider.startAutoCycle()
    }

    fun populateProductInfo(product: ProductDetailsModel.Body) {
        var infoList: List<ProductDetailsModel.Info> = product.info!!
        for (position in 0..infoList.size - 1) {
            addField(infoList.get(position))
        }
    }

    fun addField(item: ProductDetailsModel.Info) {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.item_description, null)
        binding.productInfo!!.addView(rowView, binding.productInfo!!.childCount - 1)
        rowView.card_view.name.text = item.name
        rowView.card_view.text.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(item.text, Html.FROM_HTML_MODE_COMPACT)
        else
            Html.fromHtml(item.text)
    }
}