package com.bns.mobile.utils

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.util.Base64
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bns.mobile.R
import com.bns.mobile.presenter.screens.dashboard.DashboardViewModel
import java.security.MessageDigest
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Helper {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTime() : String {
        val timezone : ZoneId = ZoneId.systemDefault()
        return ZonedDateTime.now(timezone).format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        )
    }

    fun createSignature(params1 : String, timestamp: String) : String {
        val baseSignature = "$params1|$timestamp"
        val digest : MessageDigest = MessageDigest.getInstance("SHA-256");
        val hash = digest.digest(baseSignature.toByteArray())
        return Base64.encodeToString(hash, Base64.NO_WRAP)
    }

    fun makeToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    fun showDialogOption(
            context: Context,
            messages: String,
            onPositive: () -> Unit?,
            onNegative: () -> Unit?,
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.update_title_dialog)
        builder.setMessage(messages)

        builder.setPositiveButton(R.string.btn_yes){ dialogInterface, which ->
            onPositive()
        }

        if (!messages.contains("need update!")) {
            builder.setNegativeButton(R.string.btn_no){ dialogInterface, which ->
                onNegative()
            }
        }

        val dialog : AlertDialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }

    fun hasMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

}