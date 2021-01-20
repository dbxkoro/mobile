package com.bns.mobile.network.model.params

import com.bns.mobile.domain.model.Income
import com.google.gson.annotations.SerializedName

data class IncomeListDtoResponse (
        @SerializedName("responseCode")
        var responseCode: String? = null,
        @SerializedName("income")
        var income: List<Income>? = null,
        )