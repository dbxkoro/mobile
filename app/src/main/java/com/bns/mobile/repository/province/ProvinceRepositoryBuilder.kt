package com.bns.mobile.repository.province

import com.bns.mobile.domain.model.ProvinceList
import com.bns.mobile.network.mapper.ProvinceDtoMapper
import com.bns.mobile.network.model.params.ParamsDtoRequest
import com.bns.mobile.network.model.params.ProvinceListDtoResponse
import com.bns.mobile.network.services.ProvinceService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvinceRepositoryBuilder (
    private val service : ProvinceService,
    private val mapper : ProvinceDtoMapper,
        ) : ProvinceRepository {
    override suspend fun getListProvince(requestList: ParamsDtoRequest, onResult: (ProvinceList?) -> Unit) {
        service.getListProvince(BaseApplication.sessionId, requestList).enqueue(
            object : Callback<ProvinceListDtoResponse> {
                override fun onFailure(call: Call<ProvinceListDtoResponse>, t: Throwable) {
                    t.printStackTrace()
                    println("error ${t.message}")
                    onResult(null)
                }

                override fun onResponse(call: Call<ProvinceListDtoResponse>, responseList: Response<ProvinceListDtoResponse>) {
                    var body: ProvinceList? = null
                    val errorBody = responseList.errorBody()?.string()
                    body = if (responseList.body()?.responseCode == "00") {
                        mapper.mapToDomain(model = responseList.body())
                    } else {
                        Gson().fromJson(errorBody, ProvinceList::class.java)
                    }
                    println("RESPONSE REPO LIST PROVINCE:: ${responseList.body()}")
                    println("RESPONSE REPO ERROR LIST PROVINCE:: $errorBody")
                    println("RESPONSE REPOD LIST PROVINCE:: ${body.toString()}")
                    onResult(body)

                }
            }
        )
    }
}