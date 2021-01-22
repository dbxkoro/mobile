package com.bns.mobile.repository.params.province

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface ProvinceRepository {
    suspend fun getListProvince(requestList: ParamsDtoRequest, onResult: (ParamList?) -> Unit)
}