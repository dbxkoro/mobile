package com.bns.mobile.presenter.screens.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bns.mobile.di.module.AppModule.PreferenceProvide
import com.bns.mobile.domain.model.Balance
import com.bns.mobile.network.model.balance.BalanceDtoRequest
import com.bns.mobile.network.model.user.UserDtoRequest
import com.bns.mobile.repository.balance.BalanceRepository
import com.bns.mobile.repository.user.UserRepository
import com.bns.mobile.utils.Helper
import kotlinx.coroutines.launch
import java.io.IOException

@RequiresApi(Build.VERSION_CODES.O)
class DashboardViewModel
    @ViewModelInject
    constructor(
       private val pref : PreferenceProvide,
       private val balance : BalanceRepository,
       private val user : UserRepository
    )
    : ViewModel() {

        private val helper = Helper
        val isLoading: MutableState<Boolean> = mutableStateOf(true)
        val isExpired: MutableState<Boolean> = mutableStateOf(false)
        private val userId = pref.getPrefString("BNSMOBILE_USERNAME")

    init {
        getUser()
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
//                         "31" -> isExpiredSession()
//                         "00" -> isLoading.value = false
                     }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun getUser() {
            val idPartner = "Partner-001"
            val timestamp = helper.getCurrentTime()
            val signature = helper.createSignature(idPartner, timestamp)
            val requestParams = UserDtoRequest(
                idPartner,
                timestamp,
                signature,
                userId,
            )
            viewModelScope.launch {
                try {
                    user.getUserInfo(request = requestParams) {
                        println("VIEW MODEL USER INFO RESPONSE :: $it")
                    }
                } catch (e : IOException) {
                    e.printStackTrace()
                }
            }
    }


    fun isExpiredSession() {
        isExpired.value = true
    }


}