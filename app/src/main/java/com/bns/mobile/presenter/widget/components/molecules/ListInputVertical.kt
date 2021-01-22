package com.bns.mobile.presenter.widget.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bns.mobile.domain.model.Param

@Composable
fun <T> ListInputVertical(
        list : List<T> = listOf<T>(),
        child: @Composable (dataModal : T) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        itemsIndexed(list) {_, item ->
            child(item)
            Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
        }
    }
}