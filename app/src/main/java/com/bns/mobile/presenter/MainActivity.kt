package com.bns.mobile.presenter

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.bns.mobile.R
import com.bns.mobile.di.module.AppModule.PreferenceProvide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    lateinit var handler: Handler
//    lateinit var runnable: Runnable

//    @Inject
//    lateinit var prefs : PreferenceProvide

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: 19/01/21 Handle timeout
        // THIS CODE RUNNING AUTO LOG OUT EVERY 10 SEC
//        handler = Handler(Looper.getMainLooper())
//        runnable = Runnable {
//            showDialog()
//        }
//
//        if (prefs.getPrefString("BNSMOBILE_SESSION") != "") {
//            startHandler()
//        }

    }


//    override fun onUserInteraction() {
//        super.onUserInteraction()
//        stopHandler()
//        startHandler()
//    }

//    private fun showDialog() {
//      var builder = AlertDialog.Builder(this)
//        builder.setTitle("Timeout")
//        builder.setMessage("Kami tak menemukan interaksi apapun, kamu masih aktif?")
//        builder.setPositiveButton(
//            "Yes") { dialog, id ->
//            startHandler()
//        }
//        builder.setNegativeButton(
//            "No") { dialog, id ->
//            inActivityDetect()
//        }
//        builder.show()
//
//    }
//    private fun inActivityDetect() {
//            Toast.makeText(this, "Auto Log Out - No Interaction Detect", Toast.LENGTH_LONG).show()
//            prefs.deletePrefString("BNSMOBILE_SESSION")
//            Navigation.findNavController(this, R.id.main_nav_host_fragment).navigate(R.id.loginScreen)
//    }

//    private fun stopHandler() {
//        handler.removeCallbacks(runnable)
//    }
//    private fun startHandler() {
//        handler.postDelayed(runnable, 10000.toLong())
//    }
}