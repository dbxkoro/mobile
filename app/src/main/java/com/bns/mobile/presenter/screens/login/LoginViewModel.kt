package com.bns.mobile.presenter.screens.login

import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bns.mobile.di.module.AppModule.PreferenceProvide
import com.bns.mobile.domain.model.Auth
import com.bns.mobile.network.model.auth.AuthDtoRequest
import com.bns.mobile.network.model.server.KeyDtoRequest
import com.bns.mobile.presenter.BaseApplication
import com.bns.mobile.repository.auth.AuthRepository
import com.bns.mobile.repository.server.KeyRepository
import com.bns.mobile.utils.Helper
import com.bns.mobile.utils.RSA
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.KeyPair
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*
import kotlin.concurrent.schedule

@RequiresApi(Build.VERSION_CODES.O)
class LoginViewModel
@ViewModelInject
constructor(
        private val keyServer : KeyRepository,
        private val auth : AuthRepository,
        private val prefs : PreferenceProvide
) : ViewModel()
{
    private val helper = Helper
    private var rsa = RSA()
    private var publicKeyServer : PublicKey? = null
    private lateinit var rsaKeypair : KeyPair

    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val authResponse : MutableState<Auth> = mutableStateOf(Auth())


    fun generateKey() {
        rsaKeypair = if (helper.hasMarshmallow()) {
            rsa.createAsymmetricKeyPair()
            rsa.getAsymmetricKeyPair()!!
        }else{
            rsa.createAsymmetricKeyPair()
        }
    }

    private fun getKey() : String {
        rsaKeypair = rsa.getAsymmetricKeyPair()!!
        var key : String? =  null
        try {
                val encodedPublicKey: ByteArray = rsaKeypair.public.encoded
                val publicKeyString = Base64.encodeToString(encodedPublicKey, Base64.DEFAULT)
                val beginPem = "-----BEGIN RSA PUBLIC KEY-----\n"
                val endPem = "-----END RSA PUBLIC KEY-----"
                val pemFile = "$beginPem$publicKeyString$endPem"
                key = Base64.encodeToString(pemFile.toByteArray(), Base64.DEFAULT)
        } catch (e : IOException) {
            e.printStackTrace()
        }
        return key!!
    }

    fun proceedLogin(id: String, pw: String) {
        isLoading.value = true

        if (publicKeyServer == null) {
            requestKey(id)
        }
        Timer("Proceed Login", false)
            .schedule(1000) {
                onLogin(id, pw)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    // Return Public Key Server with pem format encoded Base64
    fun requestKey(id: String) {
//        isFailedRequestKey.value = false
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)
        val pbKey = getKey()
        val keyRequestParams = KeyDtoRequest(
                PartnerID = idPartner,
                RequestTimestamp = timestamp,
                Signature = signature,
                UserID = id,
                PublicKey = pbKey,
        )
        viewModelScope.launch{
            try {
                keyServer.getKeyServer(keyRequestParams) {
                    if(it?.publicKey != null) {
                        publicKeyServer = getPublicKeyServer(it.publicKey)
                    }
                    println("Key Response :: $it")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun getPublicKeyServer(pbKey: String?) : PublicKey {
        var publicKey : PublicKey? = null
        try {
            val pbKeyServ = Base64.decode(pbKey, Base64.DEFAULT)
            val key = String(pbKeyServ, StandardCharsets.UTF_8)
                    .replace("-----BEGIN RSA PUBLIC KEY-----", "")
                    .replace(System.lineSeparator(), "")
                    .replace("-----END RSA PUBLIC KEY-----", "")
            val decodeKey = Base64.decode(key, Base64.DEFAULT)
            val keyFactory = KeyFactory.getInstance("RSA")
            val keySpec = X509EncodedKeySpec(decodeKey)
            publicKey = keyFactory.generatePublic(keySpec)
        } catch (e : IOException) {
            e.printStackTrace()
        }
        return publicKey!!
    }

    private fun encryptPassword(pw: String) : String {
        var encryptData = "Encrypt Failed No Public Key Server"
        val jsonPassword = JSONObject()

        try {
            jsonPassword.put("password", pw)
        } catch (e: JSONException) {
            e.printStackTrace()
            println("JsonException error :: $e")
        }
        if (publicKeyServer != null) {
            encryptData = rsa.encryptJson(jsonPassword, publicKeyServer)
            encryptData = encryptData.replace("\n", "")
        }
        return encryptData
    }


    private fun onLogin(id: String, pw: String) {
        val idPartner = "Partner-001"
        val timestamp = helper.getCurrentTime()
        val signature = helper.createSignature(idPartner, timestamp)
        val encrypt = encryptPassword(pw)
        val loginParams = AuthDtoRequest(
                idPartner,
                timestamp,
                signature,
                encrypt,
                id
        )
        viewModelScope.launch {
            try {
                // TODO: 07/01/21 Save SessionId to Global Persistance
                auth.login(loginParams) {
                    authResponse.value = it!!
                    if(authResponse.value.responseCode == "00") {
                        setPreferences(id,authResponse.value.sessionId)
                        BaseApplication.sessionId = it?.sessionId!!
                    }
                    isLoading.value = false
                }
                authResponse.value = Auth()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun setPreferences(id : String, session : String?) {
        prefs.setPrefString("BNSMOBILE_USERNAME", id)
        prefs.setPrefString("BNSMOBILE_SESSION", session)
    }

}