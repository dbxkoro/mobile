package com.bns.mobile.repository.industrial

import com.bns.mobile.domain.model.IndustryList
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface IndustrialRepository {
    suspend fun getSectorList(requestList: ParamsDtoRequest, onResult: (IndustryList?) -> Unit)

}