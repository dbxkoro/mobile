package com.bns.mobile.presenter.screens.registration

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
import com.bns.mobile.repository.city.CityRepository
import com.bns.mobile.repository.degree.DegreeRepository
import com.bns.mobile.repository.income.IncomeRepository
import com.bns.mobile.repository.industrial.IndustrialRepository
import com.bns.mobile.repository.province.ProvinceRepository
import com.bns.mobile.utils.Helper
import kotlinx.coroutines.launch

class RegisterationViewModel
@ViewModelInject
constructor(
    private val province : ProvinceRepository,
    private val city : CityRepository,
    private val degree : DegreeRepository,
    private val income : IncomeRepository,
    private val industry : IndustrialRepository,
) : ViewModel()
{
    private val helper = Helper
    var listProvince: MutableState<ProvinceList> = mutableStateOf(ProvinceList())
    var listCity: MutableState<CityList> = mutableStateOf(CityList())
    var listDegree: MutableState<DegreeList> = mutableStateOf(DegreeList())
    var listIncome: MutableState<IncomeList> = mutableStateOf(IncomeList())
    var listIndustrial: MutableState<IndustryList> = mutableStateOf(IndustryList())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListProvince() {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)

        val requestParam = ParamsDtoRequest(
            idPartner,
            timestamp,
            signature,
        )

        viewModelScope.launch {
            province.getListProvince(requestParam) {
               if (it?.responseCode == "00") {
                   listProvince.value = it
               }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListCity(provinceId : String) {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)

        val requestParam = CityListDtoRequest(
                idPartner,
                timestamp,
                signature,
                provinceId
        )

        viewModelScope.launch {
            city.getListCity(requestParam) {
                if (it?.responseCode == "00") {
                    listCity.value = it
                    println("LIST CITY VM :: $it")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListDegree() {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)

        val requestParam = ParamsDtoRequest(
                idPartner,
                timestamp,
                signature,
        )

        viewModelScope.launch {
            degree.getDegreeList(requestParam) {
                if (it?.responseCode == "00") {
                    listDegree.value = it
                    println("LIST Degree VM :: $it")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListIncome() {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)

        val requestParam = ParamsDtoRequest(
                idPartner,
                timestamp,
                signature,
        )

        viewModelScope.launch {
            income.getIncomeList(requestParam) {
                if (it?.responseCode == "00") {
                    listIncome.value = it
                    println("LIST INCOME VM :: $it")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListIndustry() {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)

        val requestParam = ParamsDtoRequest(
                idPartner,
                timestamp,
                signature,
        )

        viewModelScope.launch {
            industry.getSectorList(requestParam) {
                if (it?.responseCode == "00") {
                    listIndustrial.value = it
                    println("LIST INDUSTRIAL VM :: $it")
                }
            }
        }
    }
}