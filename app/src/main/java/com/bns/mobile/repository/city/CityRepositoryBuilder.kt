package com.bns.mobile.repository.city

import com.bns.mobile.domain.model.CityList
import com.bns.mobile.network.mapper.CityDtoMapper
import com.bns.mobile.network.model.params.CityListDtoRequest
import com.bns.mobile.network.model.params.CityListDtoResponse
import com.bns.mobile.network.services.CityService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityRepositoryBuilder  (
        private val service : CityService,
        private val mapper : CityDtoMapper,
) : CityRepository {
    override suspend fun getListCity(requestList: CityListDtoRequest, onResult: (CityList?) -> Unit) {
       service.getListCity(BaseApplication.sessionId, requestList).enqueue(
               object : Callback<CityListDtoResponse> {
                   override fun onFailure(call: Call<CityListDtoResponse>, t: Throwable) {
                       t.printStackTrace()
                       println("error ${t.message}")
                       onResult(null)
                   }

                   override fun onResponse(call: Call<CityListDtoResponse>, responseList: Response<CityListDtoResponse>) {
                       var body: CityList? = null
                       val errorBody = responseList.errorBody()?.string()
                       body = if (responseList.body()?.responseCode == "00") {
                           mapper.mapToDomain(model = responseList.body())
                       } else {
                           Gson().fromJson(errorBody, CityList::class.java)
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