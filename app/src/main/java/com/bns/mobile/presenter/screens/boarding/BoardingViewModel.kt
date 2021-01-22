package com.bns.mobile.presenter.screens.boarding

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.bns.mobile.utils.Helper
import com.bns.mobile.utils.RSA
import java.security.KeyPair

class BoardingViewModel
@ViewModelInject
constructor( ) : ViewModel() {

    private var rsa = RSA()
    private val helper = Helper
    private lateinit var rsaKeypair : KeyPair

    fun generateKeyLogin() {
        rsaKeypair = if (helper.hasMarshmallow()) {
            rsa.createAsymmetricKeyPair()
            rsa.getAsymmetricKeyPair()!!
        }else{
            rsa.createAsymmetricKeyPair()
        }
    }


}