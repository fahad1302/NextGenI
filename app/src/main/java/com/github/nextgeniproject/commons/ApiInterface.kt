package com.github.nextgeniproject.commons

import com.github.nextgeniproject.models.ProductDetailsModel
import com.github.nextgeniproject.models.ProductModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("products")
    fun getProducts(): Call<ProductModel?>

    @GET("product/{product_id}")
    fun getProductDetails(@Path("product_id") productId: String?): Call<ProductDetailsModel?>
}