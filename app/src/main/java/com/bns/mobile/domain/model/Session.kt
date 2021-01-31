package com.bns.mobile.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Session {
    @SerializedName("sessionId")
    @Expose
    var sessionId: String? = null
}