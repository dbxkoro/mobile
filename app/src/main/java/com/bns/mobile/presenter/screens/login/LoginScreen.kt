package com.bns.mobile.presenter.screens.login

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bns.mobile.R
import com.bns.mobile.presenter.widget.Button
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginScreen : Fragment() {

    private val viewModel : LoginViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val isLoading = viewModel.isLoading.value
                val auth = viewModel.authResponse.value

                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (sectionForm, sectionBtn, loadingBar ) = createRefs()
                    val topGuide = createGuidelineFromTop(0.5f)

                    val username = remember { mutableStateOf("") }
                    val password = remember { mutableStateOf("") }
                    val isVisible = remember { mutableStateOf(false) }
                    val button = Button

                    fun clearText() {
                        username.value = ""
                        password.value = ""
                    }

                    fun messageToast(msg : String) {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG ).show()
                    }

                    // TODO: 14/01/21 Go To Dashboard
                    fun goToDashboard() {
                        findNavController().navigate(R.id.goToDashboard)
                    }

                    when (auth.responseCode) {
                        "103" -> messageToast("Invalid Username/Password")
                        "102" -> messageToast("Invalid Password")
                        "00" -> goToDashboard()
                        else -> println("ERROR NOT FOUND, CHECK THE LOG")
                    }

                    if (auth.sessionId != null) {
                        messageToast("${auth.sessionId}")
                    }

                    if (isLoading) {
                        LinearProgressIndicator(modifier = Modifier
                                .fillMaxWidth()
                                .constrainAs(loadingBar) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                })
                    }

                    Column(
                            modifier = Modifier
//                            .background(color = Color(0xFFDF500D))
                                    .padding(16.dp)
                                    .fillMaxWidth()
                                    .constrainAs(sectionForm) {
                                        top.linkTo(topGuide)
                                        bottom.linkTo(sectionBtn.top)
                                    }
                    ) {
                        //UserName
                        OutlinedTextField(
                                value = username.value,
                                label = { Text(text = "Username") },
                                onValueChange = {
                                    username.value = it
                                },
                                singleLine = true,
                                placeholder = {Text("Username")},
                                trailingIcon = {
                                    Icon(Icons.Filled.AccountCircle)
                                },
                                modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        //Password
                        OutlinedTextField(
                                value = password.value,
                                label = { Text(text = "Password") },
                                placeholder = {Text("Password")},
                                onValueChange = {
                                    password.value = it
                                },
                                singleLine = true,
                                trailingIcon = {
                                    Icon(
                                            if(!isVisible.value) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                            modifier = Modifier.clickable(
                                                    onClick = { isVisible.value = !isVisible.value}))
                                },

                                visualTransformation = if (!isVisible.value) PasswordVisualTransformation() else VisualTransformation.None,
                                modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .constrainAs(sectionBtn){
                                bottom.linkTo(parent.bottom, margin = 16.dp)
                            }
                    ) {
                        button.CustomButton(
                                type = "basic",
                                label = "MASUK",
                                onClick = {
                                    viewModel.proceedLogin(username.value, password.value)
                                }
                        )

//                        Button(
//                                enabled = (username.value != "" && password.value != ""),
//                                onClick = {
////                                    viewModel.proceedLogin(username.value , password.value)
//                                          },
//                                modifier = Modifier.weight(1f)
//                        ) {
//                            Text(text = "MASUK", fontSize = 21.sp)
//                        }

                    }
                }
            }
        }
    }
}