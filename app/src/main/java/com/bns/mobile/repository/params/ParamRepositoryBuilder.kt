package com.bns.mobile.repository.params

import com.bns.mobile.domain.model.Result
import com.bns.mobile.network.mapper.ResultDtoMapper
import com.bns.mobile.network.model.params.CityListDtoRequest
import com.bns.mobile.network.model.params.ParamsDtoRequest
import com.bns.mobile.network.model.server.ResultDtoResponse
import com.bns.mobile.network.services.*
import com.bns.mobile.presenter.BaseApplication
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParamRepositoryBuilder (
        private val purpose : PurposeService,
        private val province : ProvinceService,
        private val city : CityService,
        private val income : IncomeService,
        private val degree : DegreeService,
        private val industrial : IndustryService,
        private val source : SourceIncomeService,
        private val typework : TypeWorkService,
        private val mapper : ResultDtoMapper,
        ) : ParamRepository {

    override suspend fun getPurposeParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit) {
        purpose.getListPurpose(BaseApplication.sessionId, requestList).enqueue(
                object : Callback<ResultDtoResponse> {
                    override fun onFailure(call: Call<ResultDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }

                    override fun onResponse(call: Call<ResultDtoResponse>, responseList: Response<ResultDtoResponse>) {
                        var body: Result?
                        val errorBody = responseList.errorBody()?.string()
                        body = if (responseList.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = responseList.body())
                        } else {
                            Gson().fromJson(errorBody, Result::class.java)
                        }
                        onResult(body)
                    }
                }
        )
    }

    override suspend fun getProvinceParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit) {
        province.getListProvince(BaseApplication.sessionId, requestList).enqueue(
                object : Callback<ResultDtoResponse> {
                    override fun onFailure(call: Call<ResultDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }

                    override fun onResponse(call: Call<ResultDtoResponse>, responseList: Response<ResultDtoResponse>) {
                        var body: Result?
                        val errorBody = responseList.errorBody()?.string()
                        body = if (responseList.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = responseList.body())
                        } else {
                            Gson().fromJson(errorBody, Result::class.java)
                        }
                        onResult(body)
                    }
                }
        )
    }

    override suspend fun getCityParams(requestList: CityListDtoRequest, onResult: (Result?) -> Unit) {
        city.getListCity(BaseApplication.sessionId, requestList).enqueue(
                object : Callback<ResultDtoResponse> {
                    override fun onFailure(call: Call<ResultDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }

                    override fun onResponse(call: Call<ResultDtoResponse>, responseList: Response<ResultDtoResponse>) {
                        var body: Result?
                        val errorBody = responseList.errorBody()?.string()
                        body = if (responseList.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = responseList.body())
                        } else {
                            Gson().fromJson(errorBody, Result::class.java)
                        }
                        onResult(body)
                    }
                }
        )
    }

    override suspend fun getIncomeParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit) {
        income.getListIncome(BaseApplication.sessionId, requestList).enqueue(
                object : Callback<ResultDtoResponse> {
                    override fun onFailure(call: Call<ResultDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }

                    override fun onResponse(call: Call<ResultDtoResponse>, responseList: Response<ResultDtoResponse>) {
                        var body: Result?
                        val errorBody = responseList.errorBody()?.string()
                        body = if (responseList.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = responseList.body())
                        } else {
                            Gson().fromJson(errorBody, Result::class.java)
                        }
                        onResult(body)
                    }
                }
        )
    }

    override suspend fun getDegreeParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit) {
        degree.getListDegree(BaseApplication.sessionId, requestList).enqueue(
                object : Callback<ResultDtoResponse> {
                    override fun onFailure(call: Call<ResultDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }

                    override fun onResponse(call: Call<ResultDtoResponse>, responseList: Response<ResultDtoResponse>) {
                        var body: Result?
                        val errorBody = responseList.errorBody()?.string()
                        body = if (responseList.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = responseList.body())
                        } else {
                            Gson().fromJson(errorBody, Result::class.java)
                        }
                        onResult(body)
                    }
                }
        )
    }

    override suspend fun getIndustrialParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit) {
        industrial.getListIndustry(BaseApplication.sessionId, requestList).enqueue(
                object : Callback<ResultDtoResponse> {
                    override fun onFailure(call: Call<ResultDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }

                    override fun onResponse(call: Call<ResultDtoResponse>, responseList: Response<ResultDtoResponse>) {
                        var body: Result?
                        val errorBody = responseList.errorBody()?.string()
                        body = if (responseList.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = responseList.body())
                        } else {
                            Gson().fromJson(errorBody, Result::class.java)
                        }
                        onResult(body)
                    }
                }
        )
    }

    override suspend fun getSourceParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit) {
        source.getListSourceIncome(BaseApplication.sessionId, requestList).enqueue(
                object : Callback<ResultDtoResponse> {
                    override fun onFailure(call: Call<ResultDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }

                    override fun onResponse(call: Call<ResultDtoResponse>, responseList: Response<ResultDtoResponse>) {
                        var body: Result?
                        val errorBody = responseList.errorBody()?.string()
                        body = if (responseList.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = responseList.body())
                        } else {
                            Gson().fromJson(errorBody, Result::class.java)
                        }
                        onResult(body)
                    }
                }
        )
    }

    override suspend fun getTypeworkParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit) {
        typework.getListWork(BaseApplication.sessionId, requestList).enqueue(
                object : Callback<ResultDtoResponse> {
                    override fun onFailure(call: Call<ResultDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }

                    override fun onResponse(call: Call<ResultDtoResponse>, responseList: Response<ResultDtoResponse>) {
                        var body: Result?
                        val errorBody = responseList.errorBody()?.string()
                        body = if (responseList.body()?.responseCode == "00") {
                            mapper.mapToDomain(model = responseList.body())
                        } else {
                            Gson().fromJson(errorBody, Result::class.java)
                        }
                        onResult(body)
                    }
                }
        )
    }

}