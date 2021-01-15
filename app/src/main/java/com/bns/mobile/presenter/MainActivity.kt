package com.bns.mobile.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bns.mobile.R
import com.bns.mobile.di.module.AppModule.PreferenceProvide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    @Inject
    lateinit var prefs : PreferenceProvide
}