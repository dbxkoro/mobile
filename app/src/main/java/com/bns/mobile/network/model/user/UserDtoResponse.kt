package com.bns.mobile.network.model.user

import com.google.gson.annotations.SerializedName

data class UserDtoResponse (
    @SerializedName("responseCode")
    var responseCode: String? = null,
    @SerializedName("responseMessage")
    var responseMessage: String? = null,
    @SerializedName("responseTimestamp")
    var responseTimestamp: String? = null,
    @SerializedName("cifNo")
    var cifNo: String? = null,
    @SerializedName("fullName")
    var fullName: String? = null,
    @SerializedName("alias")
    var alias: String? = null,
    @SerializedName("lastLogin")
    var lastLogin: String? = null,
    @SerializedName("defaultCardNo")
    var defaultCardNo: String? = null,
    @SerializedName("defaultAccountNo")
    var defaultAccountNo: String? = null,
)