package com.bns.mobile.network.model.balance

import com.google.gson.annotations.SerializedName

data class BalanceDtoRequest (
        @SerializedName("PartnerID")
        var PartnerId: String? = null,
        @SerializedName("RequestTimestamp")
        var timestamp: String? = null,
        @SerializedName("Signature")
        var signature: String? = null,
        @SerializedName("AccountNo")
        var accountNo: String? = null,
)