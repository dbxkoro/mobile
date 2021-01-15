package com.bns.mobile.repository.auth

import android.util.Log
import com.bns.mobile.domain.model.Auth
import com.bns.mobile.network.mapper.AuthDtoMapper
import com.bns.mobile.network.model.auth.AuthDtoRequest
import com.bns.mobile.network.model.auth.AuthDtoResponse
import com.bns.mobile.network.services.AuthService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepositoryBuilder (
        private val service : AuthService,
        private val mapper : AuthDtoMapper,
) : AuthRepository {

    override suspend fun login(request: AuthDtoRequest, onResult: (Auth?) -> Unit) {
        service.login(request).enqueue(
                object : Callback<AuthDtoResponse> {
                    override fun onFailure(call: Call<AuthDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }

                    override fun onResponse(call: Call<AuthDtoResponse>, response: Response<AuthDtoResponse>) {
                        var body: Auth? = null
                        val errorBody = response.errorBody()?.string()
                        body = if (response.body()?.sessionId == null) {
                            Gson().fromJson(errorBody, Auth::class.java)
                        } else {
                            mapper.mapToDomain(model = response.body())
                        }
                        Log.d("RESPONSE LOGIN E", "$errorBody")
                        Log.d("RESPONSE LOGIN", "${body.toString()}")

                        onResult(body)

                    }

//                    NOTE :: THIS CODE HANDLE RETURN ERRORBODY IF Response get Error TO AUTHRESPONSE
//                    override fun onResponse(call: Call<AuthDtoResponse>, response: Response<AuthDtoResponse>) {
//                        var body: AuthResponse? = null
//                        val errorBody = response.errorBody()?.string()
//                        body = if (response.body()?.sessionId == null) {
//                            Gson().fromJson(errorBody, AuthResponse::class.java)
//                        } else {
//                            mapper.mapToDomainModel(model = response.body())
//                        }
//                        onResult(body)
//                    }

                }
        )
    }
}