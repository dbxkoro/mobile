package com.bns.mobile.repository.province

import com.bns.mobile.domain.model.ProvinceList
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface ProvinceRepository {
    suspend fun getListProvince(requestList: ParamsDtoRequest, onResult: (ProvinceList?) -> Unit)
}