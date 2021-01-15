package com.bns.mobile.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("SPLASH ACTIVITY :: open" )
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}