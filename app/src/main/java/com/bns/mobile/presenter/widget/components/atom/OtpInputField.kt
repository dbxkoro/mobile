package com.bns.mobile.presenter.widget.components.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*
import kotlin.concurrent.schedule

@Composable
fun OTPField(modifier: Modifier = Modifier, maxLength : Int = 6, list: List<String>) {
    val fakeList = remember { mutableStateOf((1..maxLength).toList()) }
    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
        LazyRow(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            itemsIndexed(items = fakeList.value) {index , item ->
                val temp = item.toString()
                PlaceholderOtp(isFilled = temp == list.size.toString())
            }

        }
        LazyRow(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            itemsIndexed(items = list) {index , item ->

                ItemOtp(item)
            }

        }


    }
}

@Preview(showBackground = true)
@Composable
fun ItemOtp( item : String = "" ) {
    val showOtp = remember { mutableStateOf(true) }
    Timer("hide pin", false).schedule(200) {
        showOtp.value = false
    }
    if (showOtp.value) {
        Box(modifier = Modifier
                .padding(4.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = item, color = if (showOtp.value) Color.Black else Color.Transparent, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    } else {
        Box(modifier = Modifier
                .preferredSize(20.dp)
                .padding(4.dp).background( Color.Black, CircleShape))
    }

}

@Composable
fun PlaceholderOtp(isFilled : Boolean = false) {
    val isFill = Color.Transparent
    val isIdle = Color.LightGray
    val boxModifier = Modifier
            .preferredSize(20.dp)
            .padding(4.dp)

    when (isFilled) {
        true -> Box(modifier = boxModifier.background( isFill, CircleShape))
        else -> Box(modifier = boxModifier.background( isIdle, CircleShape))
    }

}