package com.bns.mobile.repository.degree

import com.bns.mobile.domain.model.DegreeList
import com.bns.mobile.network.mapper.DegreeDtoMapper
import com.bns.mobile.network.model.params.DegreeListDtoResponse
import com.bns.mobile.network.model.params.ParamsDtoRequest
import com.bns.mobile.network.services.DegreeService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DegreeRepositoryBuilder  (
        private val service : DegreeService,
        private val mapper : DegreeDtoMapper,
) : DegreeRepository {
    override suspend fun getDegreeList(requestList: ParamsDtoRequest, onResult: (DegreeList?) -> Unit) {
        service.getListCity(BaseApplication.sessionId, requestList).enqueue(
                object : Callback<DegreeListDtoResponse> {
                    override fun onFailure(call: Call<DegreeListDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }
                    override fun onResponse(call: Call<DegreeListDtoResponse>, responseList: Response<DegreeListDtoResponse>) {
                        var body: DegreeList? = null
                        val errorBody = responseList.errorBody()?.string()
                        body = if (responseList.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = responseList.body())
                        } else {
                            Gson().fromJson(errorBody, DegreeList::class.java)
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