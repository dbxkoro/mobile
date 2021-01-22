package com.bns.mobile.presenter.widget.components.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bns.mobile.domain.model.Param

@Composable
fun PickIndicator( modifier: Modifier = Modifier, selected : Param = Param()) {
    println("selected :: $selected")
    Box(modifier = modifier
            .clip(RoundedCornerShape(20))) {
        Row(modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = if (selected.name != null) "${selected.name}" else "Pick at least one")
        }
    }
}