package com.bns.mobile.network.model.params

import com.bns.mobile.domain.model.Degree
import com.google.gson.annotations.SerializedName

data class DegreeListDtoResponse (
        @SerializedName("responseCode")
        var responseCode: String? = null,
        @SerializedName("degree")
        var degree: List<Degree>? = null,
        )