package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.CityList
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.params.CityListDtoResponse

class CityDtoMapper : DomainMapper<CityListDtoResponse, CityList> {
    override fun mapToDomain(model: CityListDtoResponse?): CityList {
        return CityList(
                responseCode = model?.responseCode,
                cityList = model?.city
        )
    }

}