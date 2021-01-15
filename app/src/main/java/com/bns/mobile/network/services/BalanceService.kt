package com.bns.mobile.network.services

import com.bns.mobile.network.model.balance.BalanceDtoRequest
import com.bns.mobile.network.model.balance.BalanceDtoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface BalanceService {

    @Headers("Content-Type: application/json")
    @POST("account/inqbalance")
    fun getBalance(@Header ("SessionId") sessionId : String,
                   @Body request: BalanceDtoRequest) : Call<BalanceDtoResponse>

}