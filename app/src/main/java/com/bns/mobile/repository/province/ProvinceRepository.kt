package com.bns.mobile.repository.province

import com.bns.mobile.domain.model.ProvinceList
import com.bns.mobile.network.model.province.ProvinceListDtoRequest

interface ProvinceRepository {
    suspend fun getListProvince(requestList: ProvinceListDtoRequest, onResult: (ProvinceList?) -> Unit)
}