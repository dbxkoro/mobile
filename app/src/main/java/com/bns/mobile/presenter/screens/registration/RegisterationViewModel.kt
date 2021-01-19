package com.bns.mobile.presenter.screens.registration

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bns.mobile.domain.model.CityList
import com.bns.mobile.domain.model.ProvinceList
import com.bns.mobile.network.model.city.CityListDtoRequest
import com.bns.mobile.network.model.province.ProvinceListDtoRequest
import com.bns.mobile.repository.city.CityRepository
import com.bns.mobile.repository.province.ProvinceRepository
import com.bns.mobile.utils.Helper
import kotlinx.coroutines.launch

class RegisterationViewModel
@ViewModelInject
constructor(
    private val province : ProvinceRepository,
    private val city : CityRepository,
) : ViewModel()
{
    private val helper = Helper
    var listProvince: MutableState<ProvinceList> = mutableStateOf(ProvinceList())
    var listCity: MutableState<CityList> = mutableStateOf(CityList())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListProvince() {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)

        val requestParam = ProvinceListDtoRequest(
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
    fun getListCity() {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)

        val requestParam = CityListDtoRequest(
                idPartner,
                timestamp,
                signature,
                "11"
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
}