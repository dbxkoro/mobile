package com.bns.mobile.presenter.widget

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.Button
import androidx.navigation.findNavController
import com.bns.mobile.R

object Button {

    @Composable
    fun CustomButton(type : String, label: String, onClick : () -> Unit) {
       when(type) {
           "transparent" -> Button(
               onClick = { onClick() },
               elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
               colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
               modifier = Modifier.fillMaxWidth().fillMaxHeight(0.08f)
           ) {
               Text(text = label, style = MaterialTheme.typography.button)
           }
           "basic" -> Button(onClick = { onClick() },
               shape = RoundedCornerShape(24),
               modifier = Modifier.fillMaxWidth().fillMaxHeight(0.08f) ) {
               Text(text = label,style = MaterialTheme.typography.button)
           }

       }
       }

}