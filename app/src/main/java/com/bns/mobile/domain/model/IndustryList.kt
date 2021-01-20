package com.bns.mobile.domain.model

data class IndustryList (
        var responseCode: String? = null,
        var industrialSector: List<Industry>? = listOf<Industry>(),
        )