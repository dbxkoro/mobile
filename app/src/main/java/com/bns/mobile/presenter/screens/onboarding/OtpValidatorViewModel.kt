package com.bns.mobile.presenter.screens.onboarding

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.bns.mobile.domain.model.ParamList


class OtpValidatorViewModel
@ViewModelInject
constructor(
) : ViewModel()
{
    var pinList: MutableState<List<String>> = mutableStateOf(listOf())

    fun updateList(list: List<String>) {
        pinList.value = list
    }
}