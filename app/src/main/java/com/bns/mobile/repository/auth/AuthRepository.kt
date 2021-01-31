package com.bns.mobile.repository.auth

import com.bns.mobile.domain.model.Result
import com.bns.mobile.network.model.auth.AuthDtoRequest

interface AuthRepository {
    suspend fun login(
            request: AuthDtoRequest,
            onResult: (Result?) -> Unit
    )
}