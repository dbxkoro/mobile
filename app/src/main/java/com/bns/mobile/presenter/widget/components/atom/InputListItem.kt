package com.bns.mobile.presenter.widget.components.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bns.mobile.domain.model.Param

@Composable
fun InputListItem(
        item : Param,
        isSelected : Boolean = false,
        onSelectedChange: (Param) -> Unit,
) {
    Box(contentAlignment = Alignment.CenterStart, modifier = Modifier
            .fillMaxWidth().preferredHeight(50.dp).background(if (isSelected) Color.LightGray else Color.Transparent)
            .toggleable(value = isSelected, onValueChange = { onSelectedChange(item) })
    ) {
        Text(text = item.name!!, modifier = Modifier
                .fillMaxWidth()
        )

    }
}