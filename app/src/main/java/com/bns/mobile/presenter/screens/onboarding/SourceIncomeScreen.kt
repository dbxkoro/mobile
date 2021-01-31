package com.bns.mobile.presenter.screens.onboarding

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bns.mobile.presenter.widget.NavigationHeader
import com.bns.mobile.presenter.widget.ProgressOnboard
import com.bns.mobile.presenter.widget.components.atom.InputListItem
import com.bns.mobile.presenter.widget.components.atom.PickIndicator
import com.bns.mobile.presenter.widget.components.molecules.ListInputVertical
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceIncomeScreen : Fragment() {

    private val viewModel : SourceIncomeViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {

        viewModel.getListSourceIncome()

        return ComposeView(requireContext()).apply {
            setContent {
                val progress = remember { mutableStateOf(0.231f) }
                val listSource = viewModel.listSource.value
                val selectedSource = viewModel.selectedSource.value

                Column() {
                    NavigationHeader(title = "Pilih sumber dana", onBack = { findNavController().navigateUp() })
                    Column(modifier = Modifier.weight(1f)) {
                        ProgressOnboard(
                                progress = progress.value,
                                desc = "Yuk pilih sumber dana untuk memulai tabunganmu.",
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp))
                        PickIndicator(modifier = Modifier.padding(horizontal = 16.dp), selected = selectedSource)
                        ListInputVertical(list = listSource.list!!) { res ->
                            InputListItem(item = res, isSelected = res == selectedSource, onSelectedChange = {
                                viewModel.selectIncome(res)
                            })
                        }
                    }
                    Button(onClick = {
                    },
                            shape = RoundedCornerShape(24),
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .fillMaxHeight(0.08f))
                    {
                        Text(text = "LANJUT",style = MaterialTheme.typography.button)
                    }
                }
            }

        }


    }
}