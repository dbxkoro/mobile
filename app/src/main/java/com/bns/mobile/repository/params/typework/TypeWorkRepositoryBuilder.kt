package com.bns.mobile.repository.params.typework

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.mapper.ParamsDtoMapper
import com.bns.mobile.network.model.params.ParamsDtoRequest
import com.bns.mobile.network.model.params.ParamsDtoResponse
import com.bns.mobile.network.services.TypeWorkService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeWorkRepositoryBuilder (
        private  val service : TypeWorkService,
        private val mapper: ParamsDtoMapper
) : TypeWorkRepository
{
    override suspend fun getListWork(requestList: ParamsDtoRequest, onResult: (ParamList?) -> Unit) {
        service.getListWork(BaseApplication.sessionId, requestList).enqueue(
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
                        println("RESPONSE REPO LIST TypeWorkList:: ${responseList.body()}")
                        println("RESPONSE REPO ERROR LIST TypeWorkList:: $errorBody")
                        println("RESPONSE REPOD LIST TypeWorkList:: ${body.toString()}")
                        onResult(body)

                    }
                }
        )
    }
}