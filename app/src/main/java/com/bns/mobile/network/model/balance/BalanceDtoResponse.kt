package com.bns.mobile.network.model.balance

import com.google.gson.annotations.SerializedName

data class BalanceDtoResponse (
        @SerializedName("responseCode")
        var responseCode: String? = null,
        @SerializedName("responseMessage")
        var responseMessage: String? = null,
        @SerializedName("responseTimestamp")
        var responseTimestamp: String? = null,
        @SerializedName("accountNo")
        var accountNo: String? = null,
        @SerializedName("accountName")
        var accountName: String? = null,
        @SerializedName("accountCurrency")
        var accountCurrency: String? = null,
        @SerializedName("accountBalance")
        var accountBalance: String? = null,
)