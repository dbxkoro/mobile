package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.Result
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.server.ResultDtoResponse

class ResultDtoMapper : DomainMapper<ResultDtoResponse, Result> {
    override fun mapToDomain(model: ResultDtoResponse?): Result {
        return Result(
            responseCode = model?.responseCode,
            responseMessage = model?.responseMessage,
            data = model?.data,
        )
    }
}