package com.bns.mobile.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Key {
    @SerializedName("publicKey")
    @Expose
    var publicKey: String? = null
}