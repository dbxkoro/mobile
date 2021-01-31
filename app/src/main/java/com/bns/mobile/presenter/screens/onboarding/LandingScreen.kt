package com.bns.mobile.presenter.screens.onboarding

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bns.mobile.R
import com.bns.mobile.presenter.widget.NavigationHeader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingScreen : Fragment() {


    private val viewModel : LandingViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {

            setContent {
                    Column() {
                        NavigationHeader( onBack = {findNavController().navigateUp()})
                        Column(modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .weight(1f)
                                ){
                            Text(text = "Yang perlu kamu\nketahui" , style = MaterialTheme.typography.h4.merge(
                                    SpanStyle(fontWeight = FontWeight.Bold)
                            ))
                            Text("buka rekening 5 menit jadi, gaskeun cuy", modifier = Modifier.padding(vertical = 8.dp))
                            Box(modifier = Modifier.fillMaxHeight().fillMaxWidth().background(Color.Green)){
                            }
                      }

                        Button(onClick = {
                                         findNavController().navigate(R.id.toEmailValidator)
                                         },
                          shape = RoundedCornerShape(24),
                          modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(16.dp)
                                  .fillMaxHeight(0.08f))
                               {
                          Text(text = "MULAI",style = MaterialTheme.typography.button)
                      }



                }
            }
        }
    }
}