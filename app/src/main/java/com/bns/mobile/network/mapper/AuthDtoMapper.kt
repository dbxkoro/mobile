package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.Auth
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.auth.AuthDtoResponse

class AuthDtoMapper : DomainMapper<AuthDtoResponse, Auth> {
    override fun mapToDomain(model: AuthDtoResponse?): Auth {
        return Auth(
                responseMessage = model?.responseMessage,
                responseCode = model?.responseCode,
                sessionId = model?.sessionId,
        )
    }


}