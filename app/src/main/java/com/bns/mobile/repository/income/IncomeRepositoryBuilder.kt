package com.bns.mobile.repository.income

import com.bns.mobile.domain.model.IncomeList
import com.bns.mobile.network.mapper.IncomeDtoMapper
import com.bns.mobile.network.model.params.IncomeListDtoResponse
import com.bns.mobile.network.model.params.ParamsDtoRequest
import com.bns.mobile.network.services.IncomeService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IncomeRepositoryBuilder (
        private val service : IncomeService,
        private val mapper : IncomeDtoMapper,
        ) : IncomeRepository {

    override suspend fun getIncomeList(requestList: ParamsDtoRequest, onResult: (IncomeList?) -> Unit) {
        service.getListIncome(BaseApplication.sessionId, requestList).enqueue(
                object : Callback<IncomeListDtoResponse> {
                    override fun onFailure(call: Call<IncomeListDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }
                    override fun onResponse(call: Call<IncomeListDtoResponse>, responseList: Response<IncomeListDtoResponse>) {
                        var body: IncomeList? = null
                        val errorBody = responseList.errorBody()?.string()
                        body = if (responseList.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = responseList.body())
                        } else {
                            Gson().fromJson(errorBody, IncomeList::class.java)
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