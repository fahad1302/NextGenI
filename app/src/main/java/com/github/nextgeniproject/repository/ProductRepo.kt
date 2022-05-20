package com.github.nextgeniproject.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.nextgeniproject.commons.ApiInterface
import com.github.nextgeniproject.commons.MyApplication
import com.github.nextgeniproject.models.ProductDetailsModel
import com.github.nextgeniproject.models.ProductModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepo {

    val TAG = javaClass.simpleName

    fun fetchProducts(): MutableLiveData<ProductModel> {
        var productList: MutableLiveData<ProductModel> = MutableLiveData()
        val apiInterface = MyApplication.retrofitClient!!.create(ApiInterface::class.java)

        apiInterface.getProducts()!!.enqueue(object : Callback<ProductModel?> {

            override fun onResponse(call: Call<ProductModel?>, response: Response<ProductModel?>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        productList.value = response.body()!!
                    }
                }
            }

            override fun onFailure(call: Call<ProductModel?>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

        })
        return productList
    }

    fun fetchProductDetails(product_id: String?): MutableLiveData<ProductDetailsModel> {
        var productDetails: MutableLiveData<ProductDetailsModel> = MutableLiveData()
        val apiInterface = MyApplication.retrofitClient!!.create(ApiInterface::class.java)

        apiInterface.getProductDetails(product_id).enqueue(object : Callback<ProductDetailsModel?> {

            override fun onResponse(call: Call<ProductDetailsModel?>, response: Response<ProductDetailsModel?>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        productDetails.value = response.body()!!
                    }
                }
            }

            override fun onFailure(call: Call<ProductDetailsModel?>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

        })
        return productDetails
    }
}