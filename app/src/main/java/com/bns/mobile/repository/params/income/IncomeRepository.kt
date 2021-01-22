package com.bns.mobile.repository.params.income

import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface IncomeRepository {
    suspend fun getIncomeList(requestList: ParamsDtoRequest, onResult: (ParamList?) -> Unit)

}