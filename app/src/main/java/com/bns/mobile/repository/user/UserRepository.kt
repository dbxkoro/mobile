package com.bns.mobile.repository.user

import com.bns.mobile.domain.model.User
import com.bns.mobile.network.model.user.UserDtoRequest

interface UserRepository {
    suspend fun getUserInfo(request: UserDtoRequest, onResult: (User?) -> Unit)
}