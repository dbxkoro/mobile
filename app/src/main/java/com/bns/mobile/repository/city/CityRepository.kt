package com.bns.mobile.repository.city

import com.bns.mobile.domain.model.CityList
import com.bns.mobile.network.model.params.CityListDtoRequest

interface CityRepository {
    suspend fun getListCity(requestList: CityListDtoRequest, onResult: (CityList?) -> Unit)

}