package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.IndustryList
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.params.IndustryListDtoResponse

class IndustryDtoMapper : DomainMapper<IndustryListDtoResponse, IndustryList> {
    override fun mapToDomain(model: IndustryListDtoResponse?): IndustryList {
        return IndustryList(
                responseCode = model?.responseCode,
                industrialSector = model?.industrialSector,
        )
    }
}