package com.bns.mobile.repository.params.typework

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface TypeWorkRepository {
    suspend fun getListWork(requestList: ParamsDtoRequest, onResult: (ParamList?) -> Unit)

}