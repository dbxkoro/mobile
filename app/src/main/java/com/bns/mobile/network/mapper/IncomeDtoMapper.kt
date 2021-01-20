package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.IncomeList
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.params.IncomeListDtoResponse

class IncomeDtoMapper : DomainMapper<IncomeListDtoResponse, IncomeList> {
    override fun mapToDomain(model: IncomeListDtoResponse?): IncomeList {
       return IncomeList(
               responseCode = model?.responseCode,
               income = model?.income!!
       )
    }

}