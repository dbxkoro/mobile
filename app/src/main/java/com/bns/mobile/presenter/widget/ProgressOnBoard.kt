package com.bns.mobile.presenter.widget

import androidx.compose.animation.animate
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

@Composable
fun ProgressOnboard(modifier: Modifier = Modifier, progress : Float, desc : String = "") {
    val animatedProgress = animate(
            target = progress,
            animSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.clip(RoundedCornerShape(50)).weight(2f).preferredHeight(15.dp)){
                LinearProgressIndicator(progress = animatedProgress, modifier = Modifier.scale(scaleX = 1f, scaleY = 8f).fillMaxWidth())
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "1/13")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = desc)
        Spacer(modifier = Modifier.height(16.dp))
    }
}