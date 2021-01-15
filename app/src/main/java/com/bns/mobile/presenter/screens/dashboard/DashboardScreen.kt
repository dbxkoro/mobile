package com.bns.mobile.presenter.screens.dashboard


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bns.mobile.R
import com.bns.mobile.presenter.widget.dialog.ProxyDialog
import com.bns.mobile.presenter.widget.dialog.SessionDialog
import com.bns.mobile.utils.Helper
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class DashboardScreen : Fragment() {

    private val viewModel : DashboardViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val balance = viewModel.currentBalance.value


                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (dash, loading)  = createRefs()

                    val helper = Helper
                    val isLoading = viewModel.isLoading.value

                    fun expiredSession() {
                           viewModel.onLogOut()
                           findNavController().navigate(R.id.logOut)
                    }

                    if (viewModel.isExpired.value) {
                        println("Balance $balance")
                        SessionDialog(balance.responseMessage) { expiredSession() }.show(
                            childFragmentManager, SessionDialog.TAG)
                    }

                    if (isLoading) {
                        CircularProgressIndicator(
                            strokeWidth = 10.dp,
                            modifier = Modifier
                                .preferredSize(70.dp)
                                .constrainAs(loading) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                })
                    } else {
                        Column(modifier = Modifier.constrainAs(dash) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                            Text(text = "${balance.accountCurrency} ${balance.accountBalance}",
                                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.ExtraBold),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = {
                                viewModel.onLogOut()
                                findNavController().navigate(R.id.logOut)
                            }) {
                                Text(text = "Clear Pref / Log Out")

                            }
                        }
                    }


                }
            }
        }
    }

}
