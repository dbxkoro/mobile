package com.bns.mobile.presenter.widget.components.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bns.mobile.domain.model.Param

@Composable
fun GridItem(
        item : Param,
        isSelected : Boolean = false,
        onSelectedChange: (Param) -> Unit,
        idleColor : Color = MaterialTheme.colors.secondaryVariant,
        selectedColor : Color = MaterialTheme.colors.secondary
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier
            .clip(RoundedCornerShape(10))
            .fillMaxSize()
            .background(if(isSelected) selectedColor else idleColor)
            .toggleable(value = isSelected, onValueChange = { onSelectedChange(item) })
    ) {
        Text(text = "${item.name}")
    }
}