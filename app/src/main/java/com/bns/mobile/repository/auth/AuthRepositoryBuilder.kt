package com.bns.mobile.repository.auth

import com.bns.mobile.domain.model.Result
import com.bns.mobile.network.mapper.ResultDtoMapper
import com.bns.mobile.network.model.auth.AuthDtoRequest
import com.bns.mobile.network.model.server.ResultDtoResponse
import com.bns.mobile.network.services.AuthService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepositoryBuilder (
        private val service : AuthService,
        private val mapper : ResultDtoMapper,
) : AuthRepository {

    override suspend fun login(request: AuthDtoRequest, onResult: (Result?) -> Unit) {
        service.login(request).enqueue(
                object : Callback<ResultDtoResponse> {
                    override fun onFailure(call: Call<ResultDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }

                    override fun onResponse(call: Call<ResultDtoResponse>, response: Response<ResultDtoResponse>) {
                        println("RESPON SERVICE AUTH:: ${response.body()}")
                        var responseResult: Result
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