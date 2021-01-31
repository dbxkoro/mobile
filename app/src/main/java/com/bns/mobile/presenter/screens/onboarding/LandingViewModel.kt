package com.bns.mobile.presenter.screens.onboarding

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bns.mobile.domain.model.*
import com.bns.mobile.network.model.params.CityListDtoRequest
import com.bns.mobile.network.model.params.ParamsDtoRequest
import com.bns.mobile.repository.params.ParamRepository
import com.bns.mobile.utils.Helper
import kotlinx.coroutines.launch

class LandingViewModel
@ViewModelInject
constructor(
private val params : ParamRepository
) : ViewModel()
{
    private val helper = Helper
    var listProvince: MutableState<ParamList> = mutableStateOf(ParamList())
    var listCity: MutableState<ParamList> = mutableStateOf(ParamList())
    var listDegree: MutableState<ParamList> = mutableStateOf(ParamList())
    var listIncome: MutableState<ParamList> = mutableStateOf(ParamList())
    var listIndustrial: MutableState<ParamList> = mutableStateOf(ParamList())
    var listSourceIncome : MutableState<ParamList> = mutableStateOf(ParamList())
    var listWork : MutableState<ParamList> = mutableStateOf(ParamList())
    var listPurpose : MutableState<ParamList> = mutableStateOf(ParamList())
    var selectedPurpose : MutableState<Param> = mutableStateOf(Param())

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getListProvince() {
//        val idPartner = "Partner-001"
//        val timestamp = helper.getCurrentTime()
//        val signature = helper.createSignature(idPartner, timestamp)
//
//        val requestParam = ParamsDtoRequest(
//            idPartner,
//            timestamp,
//            signature,
//        )
//
//        viewModelScope.launch {
//            province.getListProvince(requestParam) {
//               if (it?.responseCode == "00") {
//                   listProvince.value = it
//               }
//            }
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getListCity(provinceId : String) {
//        val idPartner = "Partner-001"
//        val timestamp = helper.getCurrentTime()
//        val signature = helper.createSignature(idPartner, timestamp)
//
//        val requestParam = CityListDtoRequest(
//                idPartner,
//                timestamp,
//                signature,
//                provinceId
//        )
//
//        viewModelScope.launch {
//            city.getListCity(requestParam) {
//                if (it?.responseCode == "00") {
//                    listCity.value = it
//                    println("LIST CITY VM :: $it")
//                }
//            }
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getListDegree() {
//        val idPartner = "Partner-001"
//        val timestamp = helper.getCurrentTime()
//        val signature = helper.createSignature(idPartner, timestamp)
//
//        val requestParam = ParamsDtoRequest(
//                idPartner,
//                timestamp,
//                signature,
//        )
//
//        viewModelScope.launch {
//            degree.getDegreeList(requestParam) {
//                if (it?.responseCode == "00") {
//                    listDegree.value = it
//                    println("LIST Degree VM :: $it")
//                }
//            }
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getListIncome() {
//        val idPartner = "Partner-001"
//        val timestamp = helper.getCurrentTime()
//        val signature = helper.createSignature(idPartner, timestamp)
//
//        val requestParam = ParamsDtoRequest(
//                idPartner,
//                timestamp,
//                signature,
//        )
//
//        viewModelScope.launch {
//            income.getIncomeList(requestParam) {
//                if (it?.responseCode == "00") {
//                    listIncome.value = it
//                    println("LIST INCOME VM :: $it")
//                }
//            }
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getListIndustry() {
//        val idPartner = "Partner-001"
//        val timestamp = helper.getCurrentTime()
//        val signature = helper.createSignature(idPartner, timestamp)
//
//        val requestParam = ParamsDtoRequest(
//                idPartner,
//                timestamp,
//                signature,
//        )
//
//        viewModelScope.launch {
//            industry.getSectorList(requestParam) {
//                if (it?.responseCode == "00") {
//                    listIndustrial.value = it
//                    println("LIST INDUSTRIAL VM :: $it")
//                }
//            }
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getListPurpose() {
//        val idPartner = "Partner-001"
//        val timestamp = helper.getCurrentTime()
//        val signature = helper.createSignature(idPartner, timestamp)
//
//        val requestParam = ParamsDtoRequest(
//                idPartner,
//                timestamp,
//                signature,
//        )
//
//        viewModelScope.launch {
//            purpose.getPurpose(requestParam) {
//                if (it?.responseCode == "00") {
//                    listPurpose.value = it
//                    println("LIST PURPOSE VM :: $it")
//                }
//            }
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getListSourceIncome() {
//        val idPartner = "Partner-001"
//        val timestamp = helper.getCurrentTime()
//        val signature = helper.createSignature(idPartner, timestamp)
//
//        val requestParam = ParamsDtoRequest(
//                idPartner,
//                timestamp,
//                signature,
//        )
//
//        viewModelScope.launch {
//            source.getSourceIncome(requestParam) {
//                if (it?.responseCode == "00") {
//                    listSourceIncome.value = it
//                    println("LIST PURPOSE VM :: $it")
//                }
//            }
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getListWork() {
//        val idPartner = "Partner-001"
//        val timestamp = helper.getCurrentTime()
//        val signature = helper.createSignature(idPartner, timestamp)
//
//        val requestParam = ParamsDtoRequest(
//                idPartner,
//                timestamp,
//                signature,
//        )
//
//        viewModelScope.launch {
//            work.getListWork(requestParam) {
//                if (it?.responseCode == "00") {
//                    listWork.value = it
//                    println("LIST PURPOSE VM :: $it")
//                }
//            }
//        }
//    }
}