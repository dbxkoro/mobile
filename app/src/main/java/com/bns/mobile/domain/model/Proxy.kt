package com.bns.mobile.domain.model

data class Proxy (
    var responseCode: String? = null,
    var responseMessage: String? = null,
    var status: String? = null,
    var message: String? = null,
    var url: String? = null,
    var urlUpdate: String? = null,
    var token: String? = null,
    var refreshToken: String? = null
)