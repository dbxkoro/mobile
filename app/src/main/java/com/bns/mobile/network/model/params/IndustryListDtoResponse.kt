package com.bns.mobile.network.model.params

import com.bns.mobile.domain.model.Industry
import com.google.gson.annotations.SerializedName

data class IndustryListDtoResponse (
        @SerializedName("responseCode")
        var responseCode: String? = null,
        @SerializedName("industrialSector")
        var industrialSector: List<Industry>? = null,
        )