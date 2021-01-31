package com.bns.mobile.domain.model

data class Result (
        var responseCode: String? = null,
        var responseMessage: String? = null,
        var data: Any? = null,
)
