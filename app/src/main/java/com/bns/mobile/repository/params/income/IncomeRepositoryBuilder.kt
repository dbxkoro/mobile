package com.bns.mobile.repository.params.income

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.mapper.ParamsDtoMapper
import com.bns.mobile.network.model.params.ParamsDtoRequest
import com.bns.mobile.network.model.params.ParamsDtoResponse
import com.bns.mobile.network.services.IncomeService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IncomeRepositoryBuilder (
        private val service : IncomeService,
        private val mapper : ParamsDtoMapper,
        ) : IncomeRepository {

    override suspend fun getIncomeList(requestList: ParamsDtoRequest, onResult: (ParamList?) -> Unit) {
        service.getListIncome(BaseApplication.sessionId, requestList).enqueue(
                object : Callback<ParamsDtoResponse> {
                    override fun onFailure(call: Call<ParamsDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }
                    override fun onResponse(call: Call<ParamsDtoResponse>, responseList: Response<ParamsDtoResponse>) {
                        var body: ParamList? = null
                        val errorBody = responseList.errorBody()?.string()
                        body = if (responseList.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = responseList.body())
                        } else {
                            Gson().fromJson(errorBody, ParamList::class.java)
                        }
                        println("RESPONSE REPO LIST INCOME:: ${responseList.body()}")
                        println("RESPONSE REPO ERROR LIST INCOME:: $errorBody")
                        println("RESPONSE REPOD LIST INCOME:: ${body.toString()}")
                        onResult(body)

                    }
                }
        )
    }
}