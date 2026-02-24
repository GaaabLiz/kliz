package io.github.gaaabliz.kliz.compose.common.ui.progress

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.uniupo

@Composable
fun DashboardProgress(
    status : Boolean = false
) {
    if(status) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = uniupo,
        )
    } else {
        Spacer(modifier = Modifier.height(4.dp))
    }
}