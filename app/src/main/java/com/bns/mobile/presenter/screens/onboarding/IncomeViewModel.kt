package com.bns.mobile.presenter.screens.onboarding

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bns.mobile.domain.model.Param
import com.bns.mobile.domain.model.ParamList
import com.bns.mobile.network.model.params.ParamsDtoRequest
import com.bns.mobile.repository.params.ParamRepository
import com.bns.mobile.utils.Helper
import com.google.gson.Gson
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class IncomeViewModel
@ViewModelInject
constructor(
    private val income : ParamRepository
) : ViewModel() {

    private val helper = Helper
    var listIncome: MutableState<ParamList> = mutableStateOf(ParamList())
    var selectedIncome : MutableState<Param> = mutableStateOf(Param())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListIncome() {
        viewModelScope.launch {
            val idPartner = "Partner-001"
            val timestamp = helper.getCurrentTime()
            val signature = helper.createSignature(idPartner, timestamp)

            val requestParam = ParamsDtoRequest(
                    idPartner,
                    timestamp,
                    signature,
            )
            income.getIncomeParams(requestParam) {
                if (it?.responseCode == "00") {
                    val data = Gson().fromJson(it.data.toString(), ParamList::class.java)
                    listIncome.value = data
                }
            }
        }
    }

    fun selectIncome(income : Param) {
        selectedIncome.value = income
    }

}