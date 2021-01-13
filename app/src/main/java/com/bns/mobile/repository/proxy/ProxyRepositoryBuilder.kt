package com.bns.mobile.repository.proxy

import com.bns.mobile.domain.model.Proxy
import com.bns.mobile.network.mapper.ProxyDtoMapper
import com.bns.mobile.network.model.proxy.ProxyDtoRequest
import com.bns.mobile.network.model.proxy.ProxyDtoResponse
import com.bns.mobile.network.services.ProxyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProxyRepositoryBuilder (
    private val service : ProxyService,
    private val mapper : ProxyDtoMapper,
) : ProxyRepository {

    override suspend fun checkingVersion(request: ProxyDtoRequest, onResult: (Proxy?) -> Unit) {
        service.checkingVersion(request).enqueue(
            object : Callback<ProxyDtoResponse> {
                override fun onFailure(call: Call<ProxyDtoResponse>, t: Throwable) {
                    t.printStackTrace()
                    println("error ${t.message}")
                    onResult(null)
                }
                override fun onResponse(call: Call<ProxyDtoResponse>, response: Response<ProxyDtoResponse>) {
                    val body = mapper.mapToDomain(model = response.body())
                    onResult(body)
                }
            }
        )

    }


}