package com.bns.mobile.repository.industrial

import com.bns.mobile.domain.model.IndustryList
import com.bns.mobile.network.mapper.IndustryDtoMapper
import com.bns.mobile.network.model.params.IndustryListDtoResponse
import com.bns.mobile.network.model.params.ParamsDtoRequest
import com.bns.mobile.network.services.IndustryService
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IndustrialRepositoryBuilder (
       private val service: IndustryService,
       private val mapper: IndustryDtoMapper
        ) : IndustrialRepository {
    override suspend fun getSectorList(requestList: ParamsDtoRequest, onResult: (IndustryList?) -> Unit) {
    service.getListSector(BaseApplication.sessionId, requestList).enqueue(
            object : Callback<IndustryListDtoResponse> {
                override fun onFailure(call: Call<IndustryListDtoResponse>, t: Throwable) {
                    t.printStackTrace()
                    println("error ${t.message}")
                    onResult(null)
                }

                override fun onResponse(call: Call<IndustryListDtoResponse>, responseList: Response<IndustryListDtoResponse>) {
                    var body: IndustryList? = null
                    val errorBody = responseList.errorBody()?.string()
                    body = if (responseList.body()?.responseCode == "00") {
                        mapper.mapToDomain(model = responseList.body())
                    } else {
                        Gson().fromJson(errorBody, IndustryList::class.java)
                    }
                    println("RESPONSE REPO LIST IndustryList:: ${responseList.body()}")
                    println("RESPONSE REPO ERROR LIST IndustryList:: $errorBody")
                    println("RESPONSE REPOD LIST IndustryList:: ${body.toString()}")
                    onResult(body)

                }
            }
    )
    }
}