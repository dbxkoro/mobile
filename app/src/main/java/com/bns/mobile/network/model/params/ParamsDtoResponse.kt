package com.bns.mobile.network.model.params

import com.bns.mobile.domain.model.Param
import com.google.gson.annotations.SerializedName

data class ParamsDtoResponse (
        @SerializedName("responseCode")
        var responseCode: String? = null,
        @SerializedName("list")
        var list: List<Param>? = null,
        )