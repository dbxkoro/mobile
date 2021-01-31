package com.bns.mobile.network.services

import com.bns.mobile.network.model.params.*
import com.bns.mobile.network.model.server.ResultDtoResponse
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
    ) : Call<ResultDtoResponse>
}

interface CityService {
    @Headers("Content-Type: application/json")
    @POST("param/listcity")
    fun getListCity(@Header("SessionId") sessionId : String,
                    @Body requestList: CityListDtoRequest
    ) : Call<ResultDtoResponse>
}

interface DegreeService {
    @Headers("Content-Type: application/json")
    @POST("param/listdegree")
    fun getListDegree(@Header("SessionId") sessionId : String,
                    @Body requestList: ParamsDtoRequest
    ) : Call<ResultDtoResponse>
}

interface IncomeService {
    @Headers("Content-Type: application/json")
    @POST("param/listincome")
    fun getListIncome(@Header("SessionId") sessionId : String,
                    @Body requestList: ParamsDtoRequest
    ) : Call<ResultDtoResponse>
}

interface IndustryService {
    @Headers("Content-Type: application/json")
    @POST("param/listindustrialsector")
    fun getListIndustry(@Header("SessionId") sessionId : String,
                    @Body requestList: ParamsDtoRequest
    ) : Call<ResultDtoResponse>
}

interface PurposeService {
    @Headers("Content-Type: application/json")
    @POST("param/listopeningpurpose")
    fun getListPurpose(@Header("SessionId") sessionId : String,
                    @Body requestList: ParamsDtoRequest
    ) : Call<ResultDtoResponse>
}

interface SourceIncomeService {
    @Headers("Content-Type: application/json")
    @POST("param/listsourceincome")
    fun getListSourceIncome(@Header("SessionId") sessionId : String,
                    @Body requestList: ParamsDtoRequest
    ) : Call<ResultDtoResponse>
}

interface TypeWorkService {
    @Headers("Content-Type: application/json")
    @POST("param/listtypeofwork")
    fun getListWork(@Header("SessionId") sessionId : String,
                    @Body requestList: ParamsDtoRequest
    ) : Call<ResultDtoResponse>
}