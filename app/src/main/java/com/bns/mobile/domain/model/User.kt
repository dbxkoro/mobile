package com.bns.mobile.domain.model

data class User (
    var responseCode: String? = null,
    var responseMessage: String? = null,
    var responseTimestamp: String? = null,
    var cifNo: String? = null,
    var fullName: String? = null,
    var alias: String? = null,
    var lastLogin: String? = null,
    var defaultCardNo: String? = null,
    var defaultAccountNo: String? = null,
)