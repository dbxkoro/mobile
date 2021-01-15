package com.bns.mobile.repository.auth

import com.bns.mobile.domain.model.Auth
import com.bns.mobile.network.model.auth.AuthDtoRequest

interface AuthRepository {
    suspend fun login(
            request: AuthDtoRequest,
            onResult: (Auth?) -> Unit
    )
}