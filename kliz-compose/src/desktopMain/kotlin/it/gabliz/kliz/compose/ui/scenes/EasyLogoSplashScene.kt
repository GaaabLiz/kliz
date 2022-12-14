package it.gabliz.kliz.compose.ui.scenes

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Composable
@Preview
public fun EasyLogoSplashScene(
    state : MutableState<Boolean>,
    text : MutableState<String>,
    logo : Painter = painterResource("logo.png"),
) {

    Surface(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background).padding(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = logo,
                contentDescription = "Logo",
                contentScale = ContentScale.Inside,
                modifier = Modifier.width(250.dp).height(250.dp),
            )
            if(state.value) SplashStartupStateOk(text) else SplashStartupStateError(text)
        }
    }
}

@Composable
public fun SplashStartupStateOk(text : MutableState<String>) {
    Spacer(Modifier.height(20.dp))
    Text(
        text = text.value,
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Justify,
    )
    Spacer(Modifier.height(15.dp))
    CircularProgressIndicator(color = MaterialTheme.colors.primary, strokeWidth = 4.dp)
}

@Composable
public fun SplashStartupStateError(text : MutableState<String>) {
    Spacer(Modifier.height(20.dp))
    Text(
        text = text.value,
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Justify,
    )
}