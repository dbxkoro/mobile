package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.ProvinceList
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.params.ProvinceListDtoResponse

class ProvinceDtoMapper : DomainMapper<ProvinceListDtoResponse, ProvinceList> {
    override fun mapToDomain(model: ProvinceListDtoResponse?): ProvinceList {
        return ProvinceList(
            responseCode = model?.responseCode,
            provinceList = model?.province,
        )
    }
}