package com.bns.mobile.presenter.screens.validator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bns.mobile.R
import com.bns.mobile.presenter.widget.dialog.ProxyDialog
import dagger.hilt.android.AndroidEntryPoint

//USE THIS SCREENS FOR PROXY , FILTERING NEW USER,
@AndroidEntryPoint
class ValidatorScreen : Fragment() {
    private val viewModel : ValidatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ConstraintLayout(modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.primary)) {
                    val (loading)  = createRefs()
                    val isLoading = viewModel.isLoading.value
                    val proxy = viewModel.responseProxy.value
                    if (isLoading) {
                        CircularProgressIndicator(
                            strokeWidth = 10.dp,
                            color = Color.White,
                            modifier = Modifier
                                    .preferredSize(70.dp)
                                    .constrainAs(loading) {
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    })
                    } else {
                        // TODO: 14/01/21 Showing Dialog Here
                    }

                    if(proxy.status != null) {
                        when(proxy.status) {
                            "0" -> goBoarding()
                            "1" -> ProxyDialog(proxy.message).show(
                                    childFragmentManager, ProxyDialog.TAG)
                            "2" -> ProxyDialog(proxy.message).show(
                                    childFragmentManager, ProxyDialog.TAG)
                            else -> ProxyDialog(proxy.message).show(
                                    childFragmentManager, ProxyDialog.TAG)
                        }
                    }
                }
            }
        }


    }

    private fun optPositive() {
        Toast.makeText(
                activity,
                "Let's update this apps",
                Toast.LENGTH_LONG
        ).show()
    }

 private fun goBoarding() {
     when (viewModel.isValidUser.value) {
         true -> {
             findNavController().navigate(R.id.skipToDashboard)
         }
         false -> {
//             findNavController().navigate(R.id.onBoardingScreen)
             findNavController().navigate(R.id.directPurpose)
//             findNavController().navigate(R.id.directLogin)
         }
         else -> {
             println("no nothing jon snow")
         }
     }
 }

}


//when(proxy.responseCode) {
//    "0" -> goToLoginScreen()
//    "1" -> helper.showDialogOption(this,"New version is available, may update ?", onPositive = { optPositive() }, onNegative = { goToLoginScreen() } )
//    "2" -> helper.showDialogOption(this,"New version is available, should update ?", onPositive = { optPositive() }, onNegative = { goToLoginScreen() } )
//    else -> { // Note the block
//        Helper().showDialogOption(this, "New version is available, need update!", onPositive = { optPositive() }, { null })
//    }