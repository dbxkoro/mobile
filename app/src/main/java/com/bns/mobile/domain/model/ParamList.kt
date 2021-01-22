package com.bns.mobile.domain.model

data class ParamList (
    var responseCode : String? = null,
    var list : List<Param>? = listOf(),
        )