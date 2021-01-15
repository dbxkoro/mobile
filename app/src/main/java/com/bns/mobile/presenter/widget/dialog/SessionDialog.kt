package com.bns.mobile.presenter.widget.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bns.mobile.R

class SessionDialog(private val message : String?, private val responsePositive : () -> Unit?) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.session_expired_title)
            .setMessage(message)
            .setPositiveButton(getString(R.string.btn_yes)) { _, _ -> responsePositive() }
            .create()

    companion object {
        const val TAG = "UpdateAppsConfirmationDialog"
    }
}