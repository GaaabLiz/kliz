package io.github.gaaabliz.kliz.compose.common.ui.utils


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.doveGray
import kotlinx.coroutines.CoroutineScope


val MODIFIER_ZERO_WIDTH_HEIGHT = Modifier.width(0.dp).height(0.dp)

@Composable
fun ColumnCenterText(text : String = "Ciao") {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, style = MaterialTheme.typography.body2, color = doveGray)
    }
}

@Composable
fun CoroutineColumn(
    content : @Composable (scope : CoroutineScope) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content(scope)
    }
}