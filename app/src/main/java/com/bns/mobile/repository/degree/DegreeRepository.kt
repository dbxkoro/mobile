package com.bns.mobile.repository.degree

import com.bns.mobile.domain.model.DegreeList
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface DegreeRepository {
    suspend fun getDegreeList(requestList: ParamsDtoRequest, onResult: (DegreeList?) -> Unit)

}