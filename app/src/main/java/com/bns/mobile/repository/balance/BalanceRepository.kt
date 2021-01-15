package com.bns.mobile.repository.balance

import com.bns.mobile.domain.model.Balance
import com.bns.mobile.network.model.balance.BalanceDtoRequest

interface BalanceRepository {
    suspend fun getBalance(request: BalanceDtoRequest, onResult: (Balance?) -> Unit)
}