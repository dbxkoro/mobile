package com.bns.mobile.presenter.screens.dashboard

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bns.mobile.di.module.AppModule
import com.bns.mobile.domain.model.Balance
import com.bns.mobile.network.model.balance.BalanceDtoRequest
import com.bns.mobile.repository.balance.BalanceRepository
import com.bns.mobile.utils.Helper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.IOException

@RequiresApi(Build.VERSION_CODES.O)
class DashboardViewModel
    @ViewModelInject
    constructor(
       private val pref : AppModule.PreferenceProvide,
       private val balance : BalanceRepository
    )
    : ViewModel() {

        private val helper = Helper
        val isLoading: MutableState<Boolean> = mutableStateOf(true)
        val isExpired: MutableState<Boolean> = mutableStateOf(false)

    init {
        getBalance()
    }
        fun onLogOut() {
            pref.deletePrefString("BNSMOBILE_SESSION")
        }

    val currentBalance : MutableState<Balance> = mutableStateOf(Balance())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getBalance() {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)
        val requestParams = BalanceDtoRequest(
            idPartner,
            timestamp,
            signature,
            "333666666"
        )
        viewModelScope.launch {

            try {
                 balance.getBalance(requestParams) {
                    println("VIEW MODEL BALANCE RESPONSE ${it?.responseCode}")
                    currentBalance.value = it!!
                     when(it?.responseCode) {
                         "31" -> isExpiredSession()
                         "00" -> isLoading.value = false
                     }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }



    fun isExpiredSession() {
        isExpired.value = true
    }


}