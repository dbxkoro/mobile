package com.bns.mobile.repository.params.degree

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface DegreeRepository {
    suspend fun getDegreeList(requestList: ParamsDtoRequest, onResult: (ParamList?) -> Unit)

}