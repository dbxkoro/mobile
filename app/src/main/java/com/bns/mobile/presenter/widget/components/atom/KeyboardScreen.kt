package com.bns.mobile.presenter.widget.components.atom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bns.mobile.presenter.widget.components.molecules.DynamicGridLayout

@Composable
fun KeyboardInputScreen (
        rows : Int = 3,
        space : Dp = 2.dp,
        onDeleteChar : () -> Unit,
        onInputChange : (String) -> Unit,
) {
    val listKey = (1..KeyboardLength).toList()
    DynamicGridLayout(
            list = listKey,
            space = space,
            rows = rows,
    ) { res ->
        when (res) {
            10 -> BlankSpace()
            else ->  KeyboardItem(keycode = res,
                    onAddChar = {onInputChange(it)},
                    onDeleteChar = {onDeleteChar()}
            )

        }
    }
}

@Composable
fun BlankSpace() {
    Spacer(modifier = Modifier.fillMaxSize())
}

@Composable
fun KeyboardItem(keycode : Int, onDeleteChar: () -> Unit, onAddChar: (String) -> Unit) {
    Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        var keyHint = keycode.toString()
                        if (keycode == 11) {
                            keyHint = "0"
                        }

                        when (keycode) {
                            12 -> onDeleteChar()
                            else -> onAddChar(keyHint)
                        }
                    })
    ){
        when (keycode) {
            12 -> Text(text = "Delete")
            11 -> Text(text = "0")
            else -> Text(text = keycode.toString())
        }
    }
}


private const val KeyboardLength = 12
