package com.bns.mobile.repository.params.city

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.mapper.ParamsDtoMapper
import com.bns.mobile.network.model.params.CityListDtoRequest
import com.bns.mobile.network.model.params.ParamsDtoResponse
import com.bns.mobile.network.services.CityService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityRepositoryBuilder  (
        private val service : CityService,
        private val mapper : ParamsDtoMapper,
) : CityRepository {
    override suspend fun getListCity(requestList: CityListDtoRequest, onResult: (ParamList?) -> Unit) {
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
                       println("RESPONSE REPO LIST CITY:: ${responseList.body()}")
                       println("RESPONSE REPO ERROR LIST CITY:: $errorBody")
                       println("RESPONSE REPOD LIST CITY:: ${body.toString()}")
                       onResult(body)

                   }
               }
       )
    }
}