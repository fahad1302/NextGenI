package com.github.nextgeniproject.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ProductDetailsModel {
    @SerializedName("status")
    @Expose
    private var status: Int? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("body")
    @Expose
    private var body: Body? = null

    @SerializedName("error")
    @Expose
    private var error: List<Any?>? = null

    @SerializedName("exception")
    @Expose
    private var exception: Any? = null

    @SerializedName("update_available")
    @Expose
    private var updateAvailable: Int? = null

    fun getStatus(): Int? {
        return status
    }

    fun setStatus(status: Int?) {
        this.status = status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getBody(): Body? {
        return body
    }

    fun setBody(body: Body?) {
        this.body = body
    }

    fun getError(): List<Any?>? {
        return error
    }

    fun setError(error: List<Any?>?) {
        this.error = error
    }

    fun getException(): Any? {
        return exception
    }

    fun setException(exception: Any?) {
        this.exception = exception
    }

    fun getUpdateAvailable(): Int? {
        return updateAvailable
    }

    fun setUpdateAvailable(updateAvailable: Int?) {
        this.updateAvailable = updateAvailable
    }

    class Body {
        @SerializedName("product_id")
        @Expose
        var productId: Int? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("brand_name")
        @Expose
        var brandName: String? = null

        @SerializedName("images")
        @Expose
        var images: List<String>? = null

        @SerializedName("final_price_sale")
        @Expose
        var finalPriceSale: Int? = null

        @SerializedName("info")
        @Expose
        var info: List<Info>? = null

    }

    class Info {
        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("text")
        @Expose
        var text: String? = null

    }
}