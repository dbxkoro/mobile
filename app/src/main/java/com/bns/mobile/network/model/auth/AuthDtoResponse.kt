package com.bns.mobile.network.model.auth

import com.google.gson.annotations.SerializedName

data class AuthDtoResponse (
        @SerializedName("responseCode")
        var responseCode: String? = null,
        @SerializedName("responseMessage")
        var responseMessage: String? = null,
        @SerializedName("sessionId")
        var sessionId: String? = null,
)
