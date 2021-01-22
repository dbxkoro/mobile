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
import com.bns.mobile.repository.params.purpose.PurposeRepository
import com.bns.mobile.utils.Helper
import kotlinx.coroutines.launch

class PurposeViewModel
@ViewModelInject
constructor(
        private val purpose : PurposeRepository
)
    : ViewModel() {

    private val helper  = Helper
    var listPurpose : MutableState<ParamList> = mutableStateOf(ParamList())
    var selectedPurpose : MutableState<Param> = mutableStateOf(Param())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListPurpose() {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)

        val requestParam = ParamsDtoRequest(
                idPartner,
                timestamp,
                signature,
        )

        viewModelScope.launch {
            purpose.getPurpose(requestParam) {
                if (it?.responseCode == "00") {
                    listPurpose.value = it
                    println("LIST PURPOSE VM :: $it")
                }
            }
        }
    }

    fun selectPurpose(purpose : Param) {
        selectedPurpose.value = purpose
    }

}