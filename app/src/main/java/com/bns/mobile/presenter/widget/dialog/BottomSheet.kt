package com.bns.mobile.presenter.widget.dialog

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.bns.mobile.presenter.screens.onboarding.LandingViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class BottomSheet() : BottomSheetDialogFragment() {

    private val viewModel : LandingViewModel by viewModels()

//    called on fragment like this
//    bottomSheet.show(childFragmentManager, "ShowBottomSheet")


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewModel.getListWork()

        return ComposeView(requireContext()).apply { 
            setContent {
                val work = viewModel.listWork.value

                LazyColumn(modifier = Modifier.padding(16.dp).fillMaxHeight(0.5f)){
                    itemsIndexed(items = work?.list!!){
                        index, item -> Text(text = "${index + 1}. ${item.name}")
                    }
                }
            }
        }
    }
}