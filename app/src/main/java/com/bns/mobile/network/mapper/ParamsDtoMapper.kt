package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.params.ParamsDtoResponse

class ParamsDtoMapper : DomainMapper<ParamsDtoResponse, ParamList> {
    override fun mapToDomain(model: ParamsDtoResponse?): ParamList {
        return ParamList(
                responseCode = model?.responseCode,
                list = model?.list
        )
    }

}