package com.bns.mobile.network.services

import com.bns.mobile.network.model.user.UserDtoRequest
import com.bns.mobile.network.model.user.UserDtoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @Headers("Content-Type: application/json")
    @POST("customer/userinfo")
    fun getUserInfo(@Header("SessionId") sessionId : String,
                    @Body request: UserDtoRequest) : Call<UserDtoResponse>
}