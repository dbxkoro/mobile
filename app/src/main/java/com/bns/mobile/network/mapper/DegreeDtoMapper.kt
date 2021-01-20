package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.DegreeList
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.params.DegreeListDtoResponse

class DegreeDtoMapper : DomainMapper<DegreeListDtoResponse, DegreeList> {
    override fun mapToDomain(model: DegreeListDtoResponse?): DegreeList {
        return DegreeList(
                responseCode = model?.responseCode,
                degree = model?.degree
        )
    }

}