package com.bns.mobile.network.model.params

import com.bns.mobile.domain.model.City
import com.google.gson.annotations.SerializedName

data class CityListDtoResponse (
        @SerializedName("responseCode")
        var responseCode: String? = null,
        @SerializedName("city")
        var city: List<City>? = null,
)