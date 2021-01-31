package com.bns.mobile.repository.params

import com.bns.mobile.domain.model.Result
import com.bns.mobile.network.model.params.CityListDtoRequest
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface ParamRepository {

    suspend fun getPurposeParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit)

    suspend fun getProvinceParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit)

    suspend fun getCityParams(requestList: CityListDtoRequest, onResult: (Result?) -> Unit)

    suspend fun getIncomeParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit)

    suspend fun getDegreeParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit)

    suspend fun getIndustrialParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit)

    suspend fun getSourceParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit)

    suspend fun getTypeworkParams(requestList: ParamsDtoRequest, onResult: (Result?) -> Unit)
}
