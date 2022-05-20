package com.github.nextgeniproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.nextgeniproject.repository.ProductRepo
import com.github.nextgeniproject.models.ProductDetailsModel
import com.github.nextgeniproject.models.ProductModel

class ProductViewModel(product_id: String?) : ViewModel() {

    var productRepo: ProductRepo? = null
    var productsLiveData: MutableLiveData<ProductModel>? = null
    var productDetailsLiveData: MutableLiveData<ProductDetailsModel>? = null
    var product_id: String? = null

    init {
        productRepo = ProductRepo()
        this.product_id = product_id
    }

    fun getProducts(): LiveData<ProductModel> {
        if (productsLiveData == null) {
            productsLiveData = productRepo!!.fetchProducts()
        }
        return productsLiveData!!
    }

    fun getProductDetails(): LiveData<ProductDetailsModel> {
        if (productDetailsLiveData == null) {

            productDetailsLiveData = productRepo!!.fetchProductDetails(product_id)
        }
        return productDetailsLiveData!!
    }

}