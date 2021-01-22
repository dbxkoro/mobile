package com.bns.mobile.repository.params.purpose

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface PurposeRepository {
    suspend fun getPurpose(requestList: ParamsDtoRequest, onResult: (ParamList?) -> Unit)

}