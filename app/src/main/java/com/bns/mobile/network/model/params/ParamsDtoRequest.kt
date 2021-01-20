package com.bns.mobile.network.model.params

import com.google.gson.annotations.SerializedName

data class ParamsDtoRequest (
    @SerializedName("PartnerID")
    var PartnerId: String? = null,
    @SerializedName("RequestTimestamp")
    var timestamp: String? = null,
    @SerializedName("Signature")
    var signature: String? = null,
)