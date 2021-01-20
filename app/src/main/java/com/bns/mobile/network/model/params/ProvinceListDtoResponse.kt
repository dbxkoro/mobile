package com.bns.mobile.network.model.params

import com.bns.mobile.domain.model.Province
import com.google.gson.annotations.SerializedName

data class ProvinceListDtoResponse (
    @SerializedName("responseCode")
    var responseCode: String? = null,
    @SerializedName("province")
    var province: List<Province>? = null,
)