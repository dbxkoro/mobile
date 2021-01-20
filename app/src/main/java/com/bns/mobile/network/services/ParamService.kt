package com.bns.mobile.network.services

import com.bns.mobile.network.model.params.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProvinceService {
    @Headers("Content-Type: application/json")
    @POST("param/listprovince")
    fun getListProvince(@Header("SessionId") sessionId : String,
                    @Body requestList: ParamsDtoRequest
    ) : Call<ProvinceListDtoResponse>
}

interface CityService {
    @Headers("Content-Type: application/json")
    @POST("param/listcity")
    fun getListCity(@Header("SessionId") sessionId : String,
                    @Body requestList: CityListDtoRequest
    ) : Call<CityListDtoResponse>
}

interface DegreeService {
    @Headers("Content-Type: application/json")
    @POST("param/listdegree")
    fun getListCity(@Header("SessionId") sessionId : String,
                    @Body requestList: ParamsDtoRequest
    ) : Call<DegreeListDtoResponse>
}

interface IncomeService {
    @Headers("Content-Type: application/json")
    @POST("param/listincome")
    fun getListIncome(@Header("SessionId") sessionId : String,
                    @Body requestList: ParamsDtoRequest
    ) : Call<IncomeListDtoResponse>
}