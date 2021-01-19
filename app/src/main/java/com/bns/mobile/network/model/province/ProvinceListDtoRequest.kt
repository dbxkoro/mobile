package com.bns.mobile.network.model.province

import com.google.gson.annotations.SerializedName

data class ProvinceListDtoRequest (
    @SerializedName("PartnerID")
    var PartnerId: String? = null,
    @SerializedName("RequestTimestamp")
    var timestamp: String? = null,
    @SerializedName("Signature")
    var signature: String? = null,
)