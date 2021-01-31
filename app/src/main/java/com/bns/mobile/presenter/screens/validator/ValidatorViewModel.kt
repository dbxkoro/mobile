package com.bns.mobile.presenter.screens.validator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bns.mobile.di.module.AppModule.PreferenceProvide
import com.bns.mobile.domain.model.Proxy
import com.bns.mobile.network.model.proxy.ProxyDtoRequest
import com.bns.mobile.presenter.BaseApplication
import com.bns.mobile.repository.proxy.ProxyRepository
import com.bns.mobile.repository.server.KeyRepository
import com.bns.mobile.utils.Helper
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule


@RequiresApi(Build.VERSION_CODES.O)
class ValidatorViewModel
@ViewModelInject
constructor(
        private val proxy : ProxyRepository,
        private val prefs : PreferenceProvide,
        private val keyServer : KeyRepository
) : ViewModel()
{
    companion object {
        private const val BNS_URL = "BNS_URL"
        private const val BNS_URL_UPDATED = "BNS-URL-UPDATE"
    }

    val username = prefs.getPrefString("BNSMOBILE_USERNAME")
    val sessionId = prefs.getPrefString("BNSMOBILE_SESSION")
    val isValidUser : MutableState<Boolean?> = mutableStateOf(null)
    val isLoading : MutableState<Boolean> = mutableStateOf(true)
    val responseProxy : MutableState<Proxy> = mutableStateOf(Proxy())
    private val helper = Helper

    init {
        checkingSession()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun inquiryUrl() {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)
        val paramRequest = ProxyDtoRequest(
                partnerId =  "$idPartner",
                timestamp = timestamp,
                signature = signature,
                osId = "Android",
                major = "1",
                minor = "0",
                revision = "0",
                buildNumber = "0"
        )
        viewModelScope.launch {
            proxy.checkingVersion(paramRequest){
                if (it != null) {
                    responseProxy.value = it
                    println("PROXY :: ${responseProxy.value}")
//                    BaseApplication.api_url = it.url!!
//                    setUrlPersistance(it.url,it.urlUpdate)
                }
            }

        }
    }

//    private fun setUrlPersistance(url : String?, urlUpdate : String?) {
//        prefs.setPrefString(BNS_URL_UPDATED, urlUpdate)
//        prefs.setPrefString(BNS_URL, url)
//    }

    // TODO: 14/01/21 Provide this checking session
    private fun checkingSession() {
        inquiryUrl()
        Timer("Delay on Checking Session ID", false)
            .schedule(2000){
        if (sessionId != "") {
            BaseApplication.sessionId = sessionId
            isValidUser.value = true
            } else {
                isValidUser.value = false
            }
        }
    }


}