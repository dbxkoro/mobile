package com.bns.mobile.presenter.screens.onboarding

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bns.mobile.R
import com.bns.mobile.presenter.widget.NavigationHeader
import com.bns.mobile.presenter.widget.ProgressOnboard
import com.bns.mobile.presenter.widget.components.GridView
import com.bns.mobile.presenter.widget.components.atom.GridItem
import com.bns.mobile.presenter.widget.components.atom.PickIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurposeScreen : Fragment() {

    private val viewModel : PurposeViewModel by viewModels()


    @ExperimentalLayout
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        viewModel.getListPurpose()

        return ComposeView(requireContext()).apply {
            setContent {
                val listPurpose = viewModel.listPurpose.value
                val selectedPurpose = viewModel.selectedPurpose.value
                val progress = remember { mutableStateOf(0f) }

                Column() {
                    NavigationHeader(title = "Tujuan Pembukaan", onBack = { findNavController().navigateUp() })
                    Column(modifier = Modifier.weight(1f)) {
                        ProgressOnboard(
                                progress = progress.value,
                                desc = "Yuk beri tau kami tujuan pembukaan no rekening kamu.",
                                modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                        PickIndicator(modifier = Modifier.padding(horizontal = 16.dp), selected = selectedPurpose)
                        GridView(list = listPurpose.list!!, space = 16.dp, contentPadding = 16.dp) { res ->
                            GridItem(item = res,
                                    isSelected = res == selectedPurpose,
                                    onSelectedChange = {
                                        viewModel.selectPurpose(it)
                                    }
                            )
                        }
                    }
                    Button(onClick = {
                                    findNavController().navigate(R.id.toIncomeScreen)
                    },
                            shape = RoundedCornerShape(24),
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp).fillMaxHeight(0.08f))
                    {
                        Text(text = "LANJUT",style = MaterialTheme.typography.button)
                    }

                }
            }
        }
    }
}






