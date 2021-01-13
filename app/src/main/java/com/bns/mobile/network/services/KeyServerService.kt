package com.bns.mobile.network.services


import com.bns.mobile.network.model.server.KeyDtoRequest
import com.bns.mobile.network.model.server.KeyDtoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface KeyServerService {

    @Headers("Content-Type: application/json")
    @POST("security/generatepartnerkey")
    fun requestKey(@Body request : KeyDtoRequest) : Call<KeyDtoResponse>

}