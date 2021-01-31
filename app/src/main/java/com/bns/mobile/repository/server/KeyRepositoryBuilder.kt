package com.bns.mobile.repository.server

import com.bns.mobile.network.mapper.ResultDtoMapper
import com.bns.mobile.network.model.server.KeyDtoRequest
import com.bns.mobile.network.services.KeyServerService
import com.bns.mobile.domain.model.Result
import com.bns.mobile.network.model.server.ResultDtoResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class KeyRepositoryBuilder (
        private val service: KeyServerService,
        private val mapper : ResultDtoMapper,
) : KeyRepository {

    override suspend fun getKeyServer(request: KeyDtoRequest, onResult: (Result?) -> Unit) {
        service.requestKey(request).enqueue(
                object : Callback<ResultDtoResponse> {
                    override fun onFailure(call: Call<ResultDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }
                    override fun onResponse(call: Call<ResultDtoResponse>, response: Response<ResultDtoResponse>) {

                        var responseResult : Result
                        responseResult = when (response.isSuccessful) {
                            true -> {
                                mapper.mapToDomain(model = response.body())
                            }
                            else -> {
                                Gson().fromJson(response.errorBody()?.string(), Result::class.java)
                            }
                        }
                        onResult(responseResult)

                    }
                }
        )
    }

}