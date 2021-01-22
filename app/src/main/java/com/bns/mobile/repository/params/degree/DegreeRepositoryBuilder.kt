package com.bns.mobile.repository.params.degree

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.mapper.ParamsDtoMapper
import com.bns.mobile.network.model.params.ParamsDtoRequest
import com.bns.mobile.network.model.params.ParamsDtoResponse
import com.bns.mobile.network.services.DegreeService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DegreeRepositoryBuilder  (
        private val service : DegreeService,
        private val mapper : ParamsDtoMapper,
) : DegreeRepository {
    override suspend fun getDegreeList(requestList: ParamsDtoRequest, onResult: (ParamList?) -> Unit) {
        service.getListCity(BaseApplication.sessionId, requestList).enqueue(
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
                        println("RESPONSE REPO LIST DEGREE:: ${responseList.body()}")
                        println("RESPONSE REPO ERROR LIST DEGREE:: $errorBody")
                        println("RESPONSE REPOD LIST DEGREE:: ${body.toString()}")
                        onResult(body)

                    }
                }
        )
    }

}