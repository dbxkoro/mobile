package com.bns.mobile.network.model.auth

import com.google.gson.annotations.SerializedName

data class AuthDtoRequest (
        @SerializedName("PartnerID")
        var partnerId: String? = null,
        @SerializedName("RequestTimestamp")
        var timestamp: String? = null,
        @SerializedName("Signature")
        var signature: String? = null,
        @SerializedName("EncryptValue")
        var encryptValue: String? = null,
        @SerializedName("UserID")
        var userId: String? = null
)