package com.bns.mobile.network.model.proxy

import com.google.gson.annotations.SerializedName

data class ProxyDtoRequest(
    @SerializedName("partnerID")
    var partnerId: String? = null,
    @SerializedName("requestTimestamp")
    var timestamp: String? = null,
    @SerializedName("signature")
    var signature: String? = null,
    @SerializedName("osId")
    var osId: String? = null,
    @SerializedName("major")
    var major: String? = null,
    @SerializedName("minor")
    var minor: String? = null,
    @SerializedName("revision")
    var revision: String? = null,
    @SerializedName("buildNumber")
    var buildNumber: String? = null,
)