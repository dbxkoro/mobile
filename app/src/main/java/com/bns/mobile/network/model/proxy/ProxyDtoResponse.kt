package com.bns.mobile.network.model.proxy

import com.google.gson.annotations.SerializedName

data class ProxyDtoResponse(
    @SerializedName("responseCode")
    var responseCode: String? = null,
    @SerializedName("responseMessage")
    var responseMessage: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("urlUpdate")
    var urlUpdate: String? = null,
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("refreshToken")
    var refreshToken: String? = null,
)