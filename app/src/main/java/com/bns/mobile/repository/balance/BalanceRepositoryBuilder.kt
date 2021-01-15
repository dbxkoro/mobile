package com.bns.mobile.repository.balance

import com.bns.mobile.domain.model.Balance
import com.bns.mobile.network.mapper.BalanceDtoMapper
import com.bns.mobile.network.model.balance.BalanceDtoRequest
import com.bns.mobile.network.model.balance.BalanceDtoResponse
import com.bns.mobile.network.services.BalanceService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BalanceRepositoryBuilder (
        private val service : BalanceService,
        private val mapper : BalanceDtoMapper,
) : BalanceRepository {

    override suspend fun getBalance(request: BalanceDtoRequest, onResult: (Balance?) -> Unit) {
        service.getBalance(BaseApplication.sessionId, request).enqueue(
                object : Callback<BalanceDtoResponse> {
                    override fun onFailure(call: Call<BalanceDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }

                    override fun onResponse(call: Call<BalanceDtoResponse>, response: Response<BalanceDtoResponse>) {
                        var body: Balance? = null
                        val errorBody = response.errorBody()?.string()
                        body = if (response.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = response.body())
                        } else {
                            Gson().fromJson(errorBody, Balance::class.java)
                        }
                        println("RESPONSE REPO:: ${response.body()}")
                        println("RESPONSE REPO ERROR:: $errorBody")
                        println("RESPONSE REPODD:: ${body.toString()}")
                        onResult(body)

                    }
                }
        )
    }

}