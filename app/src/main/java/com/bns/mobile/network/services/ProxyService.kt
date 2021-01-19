package com.bns.mobile.network.services

import com.bns.mobile.network.model.proxy.ProxyDtoRequest
import com.bns.mobile.network.model.proxy.ProxyDtoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProxyService {
    // TODO: 19/01/21 Implement Token Authorization wait API FIX
    @Headers("Content-Type: application/json")
    @POST("proxy/inquiryurl")
    fun checkingVersion(@Body request : ProxyDtoRequest) : Call<ProxyDtoResponse>

}