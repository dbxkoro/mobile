package com.bns.mobile.repository.params.source

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.mapper.ParamsDtoMapper
import com.bns.mobile.network.model.params.ParamsDtoRequest
import com.bns.mobile.network.model.params.ParamsDtoResponse
import com.bns.mobile.network.services.SourceIncomeService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SourceIncomeRepositoryBuilder (
        private val service : SourceIncomeService,
        private val mapper : ParamsDtoMapper,
        )  : SourceIncomeRepository {
    override suspend fun getSourceIncome(requestList: ParamsDtoRequest, onResult: (ParamList?) -> Unit) {
        service.getListSourceIncome(BaseApplication.sessionId, requestList).enqueue(
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
                        println("RESPONSE REPO LIST PurposeList:: ${responseList.body()}")
                        println("RESPONSE REPO ERROR LIST PurposeList:: $errorBody")
                        println("RESPONSE REPOD LIST PurposeList:: ${body.toString()}")
                        onResult(body)

                    }
                }
        )

    }

}