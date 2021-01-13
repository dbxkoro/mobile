package com.bns.mobile.presenter.screens.validator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

//USE THIS SCREENS FOR PROXY , FILTERING NEW USER,
class ValidatorScreen : Fragment() {
    private val viewModel : ValidatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ConstraintLayout(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.primary)) {
                    val (loading)  = createRefs()
                    val isLoading = viewModel.isLoading.value
                    if (isLoading) {
                        CircularProgressIndicator(
                            strokeWidth = 10.dp,
                            color = Color.White,
                            modifier = Modifier.preferredSize(70.dp)
                                .constrainAs(loading) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                })
                    } else {
                        findNavController().navigate(R.id.validToOnBoarding)

                    }
                }
            }
        }
    }
}
