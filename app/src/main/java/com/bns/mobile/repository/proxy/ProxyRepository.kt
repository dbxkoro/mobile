package com.bns.mobile.repository.proxy

import com.bns.mobile.domain.model.Proxy
import com.bns.mobile.network.model.proxy.ProxyDtoRequest

interface ProxyRepository {
    suspend fun checkingVersion(request : ProxyDtoRequest, onResult : (Proxy?) -> Unit)
}