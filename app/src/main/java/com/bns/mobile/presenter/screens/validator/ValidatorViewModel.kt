package com.bns.mobile.presenter.screens.validator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ValidatorViewModel
constructor() : ViewModel()
{
    val isLoading : MutableState<Boolean> = mutableStateOf(true)

    init {
        viewModelScope.launch {
            delay(3000)
            println("NAV :: Let's move to onBoarding")
            isLoading.value = false

        }
    }


}