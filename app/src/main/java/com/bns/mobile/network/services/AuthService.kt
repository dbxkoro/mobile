package com.bns.mobile.network.services

import com.bns.mobile.network.model.auth.AuthDtoRequest
import com.bns.mobile.network.model.auth.AuthDtoResponse
import com.bns.mobile.network.model.server.ResultDtoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-Type: application/json")
    @POST("security/login")
    fun login(@Body request: AuthDtoRequest) : Call<ResultDtoResponse>
}