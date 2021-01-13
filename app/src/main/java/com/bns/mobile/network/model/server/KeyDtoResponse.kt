package com.bns.mobile.network.model.server

import com.google.gson.annotations.SerializedName

data class KeyDtoResponse (
    @SerializedName("responseCode")
    var responseCode: String? = null,
    @SerializedName("responseMessage")
    var responseMessage: String? = null,
    @SerializedName("publicKey")
    var publicKey: String? = null
)