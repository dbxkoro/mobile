package com.bns.mobile.presenter.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bns.mobile.R
import com.bns.mobile.presenter.widget.NavigationHeader
import com.bns.mobile.presenter.widget.ProgressOnboard
import com.bns.mobile.presenter.widget.components.atom.PlainTextField
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailValidatorScreen : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val progress = remember { mutableStateOf(0.077f) }
                val anontext1 = "Dengan mengklik “"
                val annotatedString = with(AnnotatedString.Builder(anontext1)) {
                    pushStyle(SpanStyle(fontWeight = FontWeight.ExtraBold))
                    append("LANJUT")
                    pop()
                    append("” saya menyetui persyaratan yang berlaku yang di berikan oleh Bank Net Syariah.")
                    toAnnotatedString()
                }

                val email = remember { mutableStateOf("") }
                val phone = remember { mutableStateOf("") }
                val referral = remember { mutableStateOf("") }

                Column() {
                    NavigationHeader(title = "Daftar yuk", onBack = { findNavController().navigateUp() })
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 16.dp)) {
                        ProgressOnboard(
                                progress = progress.value,
                                desc = "Lengkapi email dan nomor hp, di bawah ini ya!.",
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp))
                        Column(verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                        .fillMaxSize()) {

                            PlainTextField(
                                    value = email.value,
                                    onValueChange = {
                                        email.value = it
                                    },
                                    label = { Text(text = "Masukan email kamu") },
                                    placeholder = {Text("example@banknetsyariah.co.id")},
                                    backgroundColor = Color.Transparent,
                                    modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            PlainTextField(
                                    value = phone.value,
                                    onValueChange = {
                                        phone.value = it
                                    },
                                    label = { Text(text = "Masukan no hp kamu") },
                                    placeholder = {Text("08123456789")},
                                    backgroundColor = Color.Transparent,
                                    modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            PlainTextField(
                                    value = referral.value,
                                    onValueChange = {
                                        referral.value = it
                                    },
                                    label = { Text(text = "Kode Referral") },
                                    backgroundColor = Color.Transparent,
                                    modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = annotatedString, modifier = Modifier.padding(horizontal = 16.dp))
                    Button(onClick = {
                                     findNavController().navigate(R.id.toOtpValidatorOnBoarding)
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