package com.bns.mobile.repository.server

import com.bns.mobile.domain.model.KeyServer
import com.bns.mobile.network.mapper.KeyDtoMapper
import com.bns.mobile.network.model.server.KeyDtoRequest
import com.bns.mobile.network.model.server.KeyDtoResponse
import com.bns.mobile.network.services.KeyServerService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class KeyRepositoryBuilder (
    private val service: KeyServerService,
    private val mapper : KeyDtoMapper,
) : KeyRepository {

    override suspend fun getKeyServer(request: KeyDtoRequest, onResult: (KeyServer?) -> Unit) {
        service.requestKey(request).enqueue(
                object : Callback<KeyDtoResponse> {
                    override fun onFailure(call: Call<KeyDtoResponse>, t: Throwable) {
                        t.printStackTrace()
                        println("error ${t.message}")
                        onResult(null)
                    }
                    override fun onResponse(call: Call<KeyDtoResponse>, response: Response<KeyDtoResponse>) {
//                        val body = mapper.mapToDomainModel(model = response.body())
                        var body: KeyServer? = null
                        val errorBody = response.errorBody()?.string()
                        body = if (response.body()?.publicKey == null) {
                            Gson().fromJson(errorBody, KeyServer::class.java)
                        } else {
                            mapper.mapToDomain(model = response.body())
                        }
                        onResult(body)
                    }
                }
        )
    }

}