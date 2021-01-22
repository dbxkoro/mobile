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
import com.bns.mobile.repository.params.source.SourceIncomeRepository
import com.bns.mobile.utils.Helper
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class SourceIncomeViewModel
@ViewModelInject
constructor(
        private val source : SourceIncomeRepository
) : ViewModel() {

    private val helper = Helper
    var listSource: MutableState<ParamList> = mutableStateOf(ParamList())
    var selectedSource : MutableState<Param> = mutableStateOf(Param())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListSourceIncome() {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)

        val requestParam = ParamsDtoRequest(
                idPartner,
                timestamp,
                signature,
        )

        viewModelScope.launch {
            source.getSourceIncome(requestParam) {
                if (it?.responseCode == "00") {
                    listSource.value = it
                    println("LIST PURPOSE VM :: $it")
                }
            }
        }
    }

    fun selectIncome(income : Param) {
        selectedSource.value = income
    }

}