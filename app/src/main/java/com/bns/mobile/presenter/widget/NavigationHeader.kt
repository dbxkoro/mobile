package com.bns.mobile.presenter.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NavigationHeader(title: String? = null , onBack : () -> Unit) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { onBack() }) {
            Icon(imageVector = Icons.Filled.KeyboardArrowLeft, modifier = Modifier.preferredSize(24.dp))
        }
            if (title != null) {
                Text(text = title, style = MaterialTheme.typography.h6.merge(SpanStyle(fontWeight = FontWeight.Bold)))
            }
    }
}