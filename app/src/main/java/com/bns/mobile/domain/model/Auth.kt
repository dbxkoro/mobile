package com.bns.mobile.domain.model

data class Auth (
    var responseCode: String? = null,
    var responseMessage: String? = null,
    var publicKey: String? = null,
)