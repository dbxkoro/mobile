package com.bns.mobile.domain.utils

interface DomainMapper <T, DomainModel> {
    fun mapToDomain(model: T?) : DomainModel
}
