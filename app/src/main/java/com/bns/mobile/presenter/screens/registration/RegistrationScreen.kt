package com.bns.mobile.presenter.screens.registration

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationScreen : Fragment() {


    private val viewModel : RegisterationViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
              ConstraintLayout( modifier = Modifier.fillMaxSize()) {
                  val (icon, content, btn) = createRefs()

                  IconButton(onClick = {
                                       findNavController().navigateUp()
                  }, modifier = Modifier.constrainAs(icon){
                      start.linkTo(parent.start)
                      top.linkTo(parent.top, 8.dp)
                  }) {
                      Icon(imageVector = Icons.Filled.ChevronLeft)
                  }


                  Column(modifier = Modifier
                          .fillMaxWidth()
                          .padding(horizontal = 16.dp)
                          .constrainAs(content) {
                              top.linkTo(icon.bottom, 8.dp)
                              start.linkTo(parent.start)
                              end.linkTo(parent.end)
                          }){
                      Text(text = "Yang perlu kamu\nketahui" , style = MaterialTheme.typography.h4.merge(
                          SpanStyle(fontWeight = FontWeight.Bold)
                      ))
                      Text("buka rekening 5 menit jadi, gaskeun cuy", modifier = Modifier.padding(vertical = 8.dp))
                  }

                  val incomes = viewModel.listIncome.value

                      LazyColumn{
                          itemsIndexed(items = incomes.income){
                              index, item -> Text(text = "$index. ${item.income}")
                          }
                      }

                      Button(onClick = {
//                          check
//                          viewModel.getListProvince()
//                          check
//                          viewModel.getListCity("11")
//                          check
//                          viewModel.getListDegree()
//                          check
//                          viewModel.getListIncome()
                      },
                          shape = RoundedCornerShape(24),
                          modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(horizontal = 16.dp)
                                  .fillMaxHeight(0.08f)
                                  .constrainAs(btn) {
                                      bottom.linkTo(parent.bottom, 16.dp)
                                  })
                               {
                          Text(text = "Mulai",style = MaterialTheme.typography.button)
                      }
              }
            }
        }
    }
}