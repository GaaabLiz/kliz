package io.github.gaaabliz.kliz.compose.android.preview

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import io.github.gaaabliz.kliz.compose.common.model.AlertType
import io.github.gaaabliz.kliz.compose.common.ui.card.AlertCard
import io.github.gaaabliz.kliz.compose.common.ui.card.CardKeyValue
import io.github.gaaabliz.kliz.compose.common.ui.card.CircleIconCard
import io.github.gaaabliz.kliz.compose.common.ui.card.LinkCard
import io.github.gaaabliz.kliz.compose.common.ui.card.PersonCard
import io.github.gaaabliz.kliz.compose.common.ui.card.DashLizCard
import io.github.gaaabliz.kliz.compose.common.ui.card.RadioAnswerCard
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun AlertCardSuccessPreview() {
    MaterialTheme {
        AlertCard(alertType = AlertType.SUCCESS)
    }
}

@Preview
@Composable
fun AlertCardErrorPreview() {
    MaterialTheme {
        AlertCard(alertType = AlertType.ERROR)
    }
}

@Preview
@Composable
fun CardKeyValuePreview() {
    MaterialTheme {
        CardKeyValue()
    }
}

@Preview
@Composable
fun LinkCardPreview() {
    MaterialTheme {
        LinkCard()
    }
}

@Preview
@Composable
fun CircleIconCardPreview() {
    MaterialTheme {
        CircleIconCard()
    }
}

@Preview
@Composable
fun RadioAnswerCardPreview() {
    MaterialTheme {
        RadioAnswerCard()
    }
}

@Preview
@Composable
fun PersonCardPreview() {
    MaterialTheme {
        PersonCard()
    }
}

@Preview
@Composable
fun DashLizCardPreview() {
    MaterialTheme {
        DashLizCard()
    }
}