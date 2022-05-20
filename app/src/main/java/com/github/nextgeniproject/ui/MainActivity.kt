package com.github.nextgeniproject.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.nextgeniproject.R
import com.github.nextgeniproject.commons.MyApplication
import com.github.nextgeniproject.adapters.ProductAdapter
import com.github.nextgeniproject.models.ProductModel
import com.github.nextgeniproject.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = resources.getString(R.string.activity_products)
        initUI()

        if (MyApplication.instance!!.isNetworkAvailable) {
            progressBar.visibility = View.VISIBLE
            val productViewModel = ProductViewModel(null)
            productViewModel.getProducts().observe(this, object : Observer<ProductModel> {
                override fun onChanged(t: ProductModel?) {
                    progressBar.setVisibility(View.GONE)
                    productAdapter.addData(t!!.getBody()!!.data!!)
                    productAdapter.notifyDataSetChanged()
                }
            })
        } else {
            Toast.makeText(this, "No Network Available", Toast.LENGTH_LONG).show()
        }

    }

    fun initUI() {
        rvList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        rvList.setLayoutManager(layoutManager)
        rvList.setItemAnimator(DefaultItemAnimator())
        productAdapter = ProductAdapter()
        rvList.adapter = productAdapter
    }

}
