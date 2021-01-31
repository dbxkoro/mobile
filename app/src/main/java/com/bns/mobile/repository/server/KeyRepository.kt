package com.bns.mobile.repository.server

import com.bns.mobile.domain.model.Result
import com.bns.mobile.network.model.server.KeyDtoRequest


interface KeyRepository {
    suspend fun getKeyServer(request: KeyDtoRequest, onResult: (Result?) -> Unit)
}