package com.bns.mobile.repository.params.source

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface SourceIncomeRepository {
    suspend fun getSourceIncome(requestList: ParamsDtoRequest, onResult: (ParamList?) -> Unit)
}