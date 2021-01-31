package com.bns.mobile.presenter.screens.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import com.bns.mobile.presenter.widget.Button
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bns.mobile.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardingScreen : Fragment() {
    private val viewModel : BoardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

         return ComposeView(requireContext()).apply {
            setContent {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val (policy, slide, action)  = createRefs()
                    val centerHorizontal = createGuidelineFromTop(0.3f)

                    val ket = "Dengan masuk atau mendaftar, kamu menyetujui\n"
                    val link1 = "Ketentuan Layanan"
                    val link2 = "Kebijakan Privasi"
                    val annotatedString = with(AnnotatedString.Builder(ket)) {
                        pushStyle(SpanStyle(fontWeight = FontWeight.ExtraBold))
                        append(link1)
                        pop()
                        append(" dan ")
                        pushStyle(SpanStyle(fontWeight = FontWeight.ExtraBold))
                        append(link2)
                        toAnnotatedString()
                    }
                    Box(modifier =  Modifier.fillMaxWidth().fillMaxHeight(fraction = 0.35f).background(Color.LightGray)
                        .constrainAs(slide) {
                            top.linkTo(centerHorizontal)
                        }){
                        Text("Slide here")
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =  Modifier.padding(horizontal = 24.dp)
                        .constrainAs(action) {
                            top.linkTo(slide.bottom, margin= 24.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                        Button(
                                onClick = {
                                    findNavController().navigate(R.id.goToRegistration)
                                },
                                modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "DAFTAR SEKARANG", fontSize = 21.sp)
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                                onClick = {
                                    findNavController().navigate(R.id.goToLogin)
                                },
                                modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "MASUK", fontSize = 21.sp)
                        }
                    }



                    Box(modifier =  Modifier.padding(horizontal = 16.dp)
                        .constrainAs(policy) {
                            bottom.linkTo(parent.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                        }) {
                        Text(text = annotatedString, style = MaterialTheme.typography.body2)
                    }

                }
            }
        }
    }
}