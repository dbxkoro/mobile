package com.bns.mobile.network.services

import com.bns.mobile.network.model.city.CityListDtoRequest
import com.bns.mobile.network.model.city.CityListDtoResponse
import com.bns.mobile.network.model.province.ProvinceListDtoRequest
import com.bns.mobile.network.model.province.ProvinceListDtoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProvinceService {
    @Headers("Content-Type: application/json")
    @POST("param/listprovince")
    fun getListProvince(@Header("SessionId") sessionId : String,
                    @Body requestList: ProvinceListDtoRequest
    ) : Call<ProvinceListDtoResponse>
}

interface CityService {
    @Headers("Content-Type: application/json")
    @POST("param/listcity")
    fun getListCity(@Header("SessionId") sessionId : String,
                    @Body requestList: CityListDtoRequest
    ) : Call<CityListDtoResponse>
}