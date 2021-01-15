package com.bns.mobile.presenter.widget.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bns.mobile.R

class ProxyDialog(private val message : String?) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireContext())
                    .setTitle(R.string.update_title_dialog)
                    .setMessage(message)
                    .setPositiveButton(getString(R.string.btn_yes)) { _,_ -> println("BTN YESSSSSS")}
                    .setNegativeButton(getString(R.string.btn_no)) { _,_ -> println("BTN NOOOOOO")}
                    .create()

    companion object {
        const val TAG = "UpdateAppsConfirmationDialog"
    }
}