package com.bns.mobile.repository.params.industrial

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface IndustrialRepository {
    suspend fun getSectorList(requestList: ParamsDtoRequest, onResult: (ParamList?) -> Unit)

}