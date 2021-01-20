package com.bns.mobile.repository.income

import com.bns.mobile.domain.model.IncomeList
import com.bns.mobile.network.model.params.ParamsDtoRequest

interface IncomeRepository {
    suspend fun getIncomeList(requestList: ParamsDtoRequest, onResult: (IncomeList?) -> Unit)

}