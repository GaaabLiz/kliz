package io.github.gaaabliz.kliz.compose.android.preview

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import io.github.gaaabliz.kliz.compose.common.ui.card.LinkCard
import io.github.gaaabliz.kliz.compose.common.ui.scaffold.LizTopAppBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LizTopAppBarPreview() {
    MaterialTheme {
        LizTopAppBar("Title")
    }
}