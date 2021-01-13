package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.KeyServer
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.server.KeyDtoResponse

class KeyDtoMapper : DomainMapper<KeyDtoResponse, KeyServer> {
    override fun mapToDomain(model: KeyDtoResponse?): KeyServer {
        return KeyServer(
            responseCode = model?.responseCode,
            responseMessage = model?.responseMessage,
            publicKey = model?.publicKey,
        )
    }
}