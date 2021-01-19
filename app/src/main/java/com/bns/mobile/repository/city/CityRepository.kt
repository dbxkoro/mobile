package com.bns.mobile.repository.city

import com.bns.mobile.domain.model.CityList
import com.bns.mobile.network.model.city.CityListDtoRequest

interface CityRepository {
    suspend fun getListCity(requestList: CityListDtoRequest, onResult: (CityList?) -> Unit)

}