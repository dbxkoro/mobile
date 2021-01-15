package com.bns.mobile.utils

import android.annotation.TargetApi
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.json.JSONObject
import java.io.IOException
import java.security.*
import java.security.spec.RSAKeyGenParameterSpec
import javax.crypto.Cipher

class RSA {
    companion object{
        const val RSA_METHOD = "RSA/ECB/PKCS1Padding"
        const val ANDROID_KEYSTORE = "AndroidKeyStore"
        const val KEY_ALIAS = "BNSMobile"
    }

    private val helper = Helper

    private fun createKeyStore(): KeyStore {
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
        keyStore.load(null)
        return keyStore
    }

    fun createAsymmetricKeyPair(): KeyPair {
        val generator: KeyPairGenerator

        if (helper.hasMarshmallow()) {
            generator = KeyPairGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_RSA,
                    ANDROID_KEYSTORE
            )
            getKeyGenParameterSpec(generator)
        } else {
            generator = KeyPairGenerator.getInstance("RSA")
            generator.initialize(2048)
        }

        return generator.generateKeyPair()
    }

    @TargetApi(23)
    private fun getKeyGenParameterSpec(generator: KeyPairGenerator) {

        val builder = KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT or KeyProperties.PURPOSE_SIGN
        )
                .setAlgorithmParameterSpec(RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4))
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
                .setUserAuthenticationRequired(false)
                .setDigests(
                        KeyProperties.DIGEST_SHA256,
                        KeyProperties.DIGEST_SHA384,
                        KeyProperties.DIGEST_SHA512
                )
                .build()

        generator.initialize(builder)
    }

    fun getAsymmetricKeyPair(): KeyPair? {
        val keyStore: KeyStore = createKeyStore()

        val privateKey = keyStore.getKey(KEY_ALIAS, null) as PrivateKey?
        val publicKey = keyStore.getCertificate(KEY_ALIAS)?.publicKey

        return if (privateKey != null && publicKey != null) {
            KeyPair(publicKey, privateKey)
        } else {
            null
        }
    }

    // Get Public Key with pem format in base64 encode
    fun getPublicKey() : String {
        val rsaKeypair = getAsymmetricKeyPair()!!
        val encodedPublicKey = rsaKeypair.public.encoded
        println(
                "Public Key Mine rsaKeyPair.public ::: ${
                    String(
                            encodedPublicKey,
                            charset("UTF-8")
                    )
                }"
        )

        val publicKeyString = Base64.encodeToString(encodedPublicKey, Base64.DEFAULT)
        val beginPem = "-----BEGIN RSA PUBLIC KEY-----\n"
        val endPem = "-----END RSA PUBLIC KEY-----"
        val pemFile = "$beginPem$publicKeyString$endPem"
        Log.d(
                "Ready Public Key ::",
                Base64.encodeToString(pemFile.toByteArray(), Base64.DEFAULT)
        )
        return Base64.encodeToString(pemFile.toByteArray(), Base64.DEFAULT)
    }

    fun removeKeyStoreKey() = createKeyStore().deleteEntry(KEY_ALIAS)

    fun encryptJson(data: JSONObject, key: Key?): String {
        val cipher: Cipher = Cipher.getInstance(RSA_METHOD)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val bytes = cipher.doFinal(data.toString().toByteArray(charset("UTF-8")))
        println("this is encrypt value :: ${Base64.encodeToString(bytes, Base64.DEFAULT)}")
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    @Throws(Exception::class)
    fun encrypt(data: String, key: Key?): String? {
        var encryptData : String? = null
        try {
            val cipher: Cipher = Cipher.getInstance(RSA_METHOD)
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val bytes = cipher.doFinal(data.toByteArray(charset("UTF-8")))
            println("this is encrypt value :: ${Base64.encodeToString(bytes, Base64.DEFAULT)}")
            encryptData = Base64.encodeToString(bytes, Base64.DEFAULT)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        }
        return encryptData
    }

    @Throws(Exception::class)
    fun decrypt(data: String?, key: Key?): String?  {
        var decryptData : String? = null
        try {
            val cipher: Cipher = Cipher.getInstance(RSA_METHOD)
            cipher.init(Cipher.DECRYPT_MODE, key)
            val encryptedData = Base64.decode(data, Base64.DEFAULT)
            val decodedData = cipher.doFinal(encryptedData)
            decryptData = String(decodedData, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        }
        return decryptData
    }

    @Throws(Exception::class)
    fun encryptText(text: String, key: Key?): String? {
        var resultText: String? = null
        try {
            Security.addProvider(BouncyCastleProvider())
            val cipher = Cipher.getInstance(RSA_METHOD, "bc")
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val data = cipher.doFinal(text.toByteArray())
            val encodeData = Base64.encode(data, Base64.DEFAULT)
            resultText = String(encodeData, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        }
        return resultText
    }



    @Throws(java.lang.Exception::class)
    fun decryptText(text: String?, key: Key?): String? {
        var resultText: String? = null
        try {
            Security.addProvider(BouncyCastleProvider())
            val cipher = Cipher.getInstance(RSA_METHOD, "bc")
            cipher.init(Cipher.DECRYPT_MODE, key)
            val data = cipher.doFinal(text?.toByteArray())
            val decodeData = Base64.decode(data, Base64.DEFAULT)
            resultText = String(decodeData, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        }
        return resultText
    }


}