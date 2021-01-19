package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.User
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.user.UserDtoResponse

class UserDtoMapper : DomainMapper<UserDtoResponse, User> {
    override fun mapToDomain(model: UserDtoResponse?): User {
        return User(
            responseMessage = model?.responseMessage,
            responseCode = model?.responseCode,
            responseTimestamp = model?.responseTimestamp,
            cifNo = model?.cifNo,
            fullName = model?.fullName,
            alias = model?.alias,
            lastLogin = model?.lastLogin,
            defaultCardNo = model?.defaultCardNo,
            defaultAccountNo = model?.defaultAccountNo,
        )
    }


}