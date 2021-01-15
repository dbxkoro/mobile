package com.bns.mobile.presenter

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application(){
    companion object{
        var api_url : String = "http://34.101.149.237:9090/"
        var proxy_url : String = "http://34.101.141.239:9099/"
        var sessionId : String = "7ede36addce3ad22f60271497b03e3a4578591a2"
    }
}