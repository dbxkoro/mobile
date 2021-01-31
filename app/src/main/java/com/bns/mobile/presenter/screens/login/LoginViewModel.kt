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
import com.bns.mobile.domain.model.Key
import com.bns.mobile.domain.model.Result
import com.bns.mobile.domain.model.Session
import com.bns.mobile.network.model.auth.AuthDtoRequest
import com.bns.mobile.network.model.server.KeyDtoRequest
import com.bns.mobile.repository.auth.AuthRepository
import com.bns.mobile.repository.server.KeyRepository
import com.bns.mobile.utils.Helper
import com.bns.mobile.utils.RSA
import com.google.gson.Gson
import kotlinx.coroutines.async
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
    val authResponse : MutableState<Result> = mutableStateOf(Result())


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

        viewModelScope.launch {
            async { generateKey() }.await()
            async { requestKey(id)  }.await()
            delay(1000)
            onLogin(id, pw)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    // Return Public Key Server with pem format encoded Base64
    fun requestKey(id: String) {
        viewModelScope.launch{
            val idPartner = "Partner-001"
            val timestamp = helper.getCurrentTime()
            val signature = helper.createSignature(idPartner, timestamp)
            val pbKey = async { getKey() }.await()
            val keyRequestParams = KeyDtoRequest(
                    PartnerID = idPartner,
                    RequestTimestamp = timestamp,
                    Signature = signature,
                    UserID = id,
                    PublicKey = pbKey,
            )

            // TODO: 29/01/21 IMPLEMENT THIS MODEL FOR ALL MODEL 

                keyServer.getKeyServer(keyRequestParams) {
                    println("IT :: $it" )
                    if (it?.responseCode == "00") {
                        val data = Gson().fromJson(it.data.toString(), Key::class.java)
                        publicKeyServer = getPublicKeyServer(data.publicKey)
                    }
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

        viewModelScope.launch {
            val idPartner = "Partner-001"
            val timestamp = helper.getCurrentTime()
            val signature = helper.createSignature(idPartner, timestamp)
            val encrypt = async { encryptPassword(pw) }.await()
            val loginParams = AuthDtoRequest(
                    idPartner,
                    timestamp,
                    signature,
                    encrypt,
                    id
            )
                auth.login(loginParams) {
                    authResponse.value = it!!
                    if (it?.responseCode == "00") {
                        val data = Gson().fromJson(it.data.toString(), Session::class.java)
                        setPreferences(id, data.sessionId)
                    }
                }
            delay(1000)
            isLoading.value = false
            authResponse.value = Result()
        }
    }

    private fun setPreferences(id : String, session : String?) {
        prefs.setPrefString("BNSMOBILE_USERNAME", id)
        prefs.setPrefString("BNSMOBILE_SESSION", session)
    }

}