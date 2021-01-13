package com.bns.mobile.network.model.server

import com.google.gson.annotations.SerializedName

data class KeyDtoRequest (
    @SerializedName("PartnerID")
    var PartnerID: String? = null,
    @SerializedName("RequestTimestamp")
    var RequestTimestamp: String? = null,
    @SerializedName("Signature")
    var Signature: String? = null,
    @SerializedName("UserID")
    var UserID: String? = null,
    @SerializedName("PublicKey")
    var PublicKey: String? = null
)