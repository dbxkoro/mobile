package com.bns.mobile.network.model.server

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ResultDtoResponse (
        @SerializedName("responseCode")
        var responseCode: String? = null,
        @SerializedName("responseMessage")
        var responseMessage: String? = null,
        @SerializedName("data")
        var data: JsonObject? = null
)