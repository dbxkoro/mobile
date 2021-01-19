package com.bns.mobile.domain.model

data class CityList (
    var responseCode: String? = null,
    var cityList: List<City>? = listOf<City>(),
        )