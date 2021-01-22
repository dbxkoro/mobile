package com.bns.mobile.presenter.widget.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> GridView(
        list : List<T> = listOf(),
        space : Dp = 16.dp,
        contentPadding: Dp = 0.dp,
        rows : Int = 2,
        child: @Composable (dataModal : T) -> Unit
) {

    WithConstraints(modifier = Modifier.padding(contentPadding)) {
        val chunked = list.chunked(rows)
        val spacer = space / rows
        val itemHeight = ((maxWidth / rows) - (spacer * (rows - 1)))
        LazyColumn(modifier = Modifier){
            itemsIndexed(chunked) { index, item ->
                val spaceV = index + 1
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(item) { indexRow, item ->
                        val grid = indexRow + 1
                        Surface(modifier = Modifier.preferredSize(itemHeight)) {
                            child(item)
                        }
                        if (grid % rows != 0){
                            Spacer(modifier = Modifier.width(space))
                        }
                    }
                }
                if (spaceV % chunked.size != 0) {
                    Spacer(modifier = Modifier.height(space))
                }
            }
        }
    }
}