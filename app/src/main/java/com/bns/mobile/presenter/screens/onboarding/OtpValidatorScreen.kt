package com.bns.mobile.presenter.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bns.mobile.presenter.widget.NavigationHeader
import com.bns.mobile.presenter.widget.components.atom.KeyboardInputScreen
import com.bns.mobile.presenter.widget.components.atom.OTPField
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpValidatorScreen : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val pin = remember { mutableStateOf("")}
                val pinList = remember { mutableStateListOf<String>() }
                Column() {
                    NavigationHeader(onBack = { findNavController().navigateUp() })
                    Column(modifier = Modifier
                            .weight(0.4f)
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()) {
                        BoxImage()
                        Text(text = "Masukkan kode OTP yang dikirim", style = MaterialTheme.typography.h6.merge(SpanStyle(fontWeight = FontWeight.Bold)))
                        Text(text = "Masukan kode unik yang telah kita kirimkan via SMS ke no +6287886624888")
                        FieldSection(list = pinList)
                    }
                    Box(modifier = Modifier
                            .weight(0.4f)
                            .padding(16.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)) {
                        KeyboardInputScreen(
                                onDeleteChar = {
                                    if (pin.value.isNotEmpty()) {
                                        pin.value = pin.value.substring(0, pin.value.length - 1)
                                        pinList.removeAt(pin.value.length)
                                    }

                                },
                                onInputChange = {
                                    if (pin.value.length < 6 ) {
                                        pin.value += it
                                        pinList.add(it)
                                    }
                                }
                        )
                    }

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun BoxImage() {
    Box(modifier = Modifier
            .preferredSize(100.dp)
            .background(Color.Red))
}

@Composable
fun FieldSection(list: List<String>) {
    Row(modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
        ){
            OTPField(modifier = Modifier
                    .weight(0.8f), maxLength = 6, list)
            CircularProgressIndicator(modifier = Modifier
                    .padding( 16.dp)
                    .preferredSize(20.dp), strokeWidth = 2.dp )
            Text(text = "03:00")
        }
}


