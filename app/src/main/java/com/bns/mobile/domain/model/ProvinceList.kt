package com.bns.mobile.domain.model

data class ProvinceList (
    var responseCode: String? = null,
    var provinceList: List<Province>? = listOf<Province>(),
)