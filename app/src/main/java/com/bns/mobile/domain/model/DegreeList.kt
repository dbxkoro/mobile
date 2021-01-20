package com.bns.mobile.domain.model

data class DegreeList (
        var responseCode: String? = null,
        var degree: List<Degree>? = listOf<Degree>(),
        )