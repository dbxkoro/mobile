package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.Proxy
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.proxy.ProxyDtoResponse


class ProxyDtoMapper : DomainMapper<ProxyDtoResponse?, Proxy> {

    override fun mapToDomain(model: ProxyDtoResponse?): Proxy {
        return Proxy(
            responseCode = model?.responseCode,
            responseMessage = model?.responseMessage,
            status = model?.status,
            message = model?.message,
            url = model?.url,
            urlUpdate = model?.urlUpdate,
            token = model?.token,
            refreshToken = model?.refreshToken,
        )
    }

}