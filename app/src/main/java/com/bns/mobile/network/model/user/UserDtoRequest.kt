package com.bns.mobile.network.model.user

import com.google.gson.annotations.SerializedName

data class UserDtoRequest (
    @SerializedName("PartnerID")
    var PartnerID: String? = null,
    @SerializedName("RequestTimestamp")
    var RequestTimestamp: String? = null,
    @SerializedName("Signature")
    var Signature: String? = null,
    @SerializedName("UserID")
    var UserID: String? = null,
)