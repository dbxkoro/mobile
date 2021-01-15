package com.bns.mobile.network.mapper

import com.bns.mobile.domain.model.Balance
import com.bns.mobile.domain.utils.DomainMapper
import com.bns.mobile.network.model.balance.BalanceDtoResponse

class BalanceDtoMapper : DomainMapper<BalanceDtoResponse, Balance> {
    override fun mapToDomain(model: BalanceDtoResponse?): Balance {
        return Balance(
                responseCode = model?.responseCode,
                responseMessage = model?.responseMessage,
                timestamp = model?.responseTimestamp,
                accountNo = model?.accountNo,
                accountName = model?.accountName,
                accountCurrency = model?.accountCurrency,
                accountBalance = model?.accountBalance
        )
    }
}