package com.bns.mobile.repository.params.city

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.model.params.CityListDtoRequest

interface CityRepository {
    suspend fun getListCity(requestList: CityListDtoRequest, onResult: (ParamList?) -> Unit)

}