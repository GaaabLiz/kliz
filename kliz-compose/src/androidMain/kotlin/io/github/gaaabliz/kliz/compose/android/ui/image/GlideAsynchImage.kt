package io.github.gaaabliz.kliz.compose.android.ui.image

/*

VERSIONE ALTERNATIVA DI ASYNCH IMAGE CON GLIDE E SHAPE CUSTOM

@OptIn(ExperimentalGlideComposeApi::class)
@Preview
@Composable
fun ShapedGlideAsyncImage(
    urlImage : String? = null,
    shape : Shape = CircleShape,
    borderStroke: BorderStroke = BorderStroke(0.dp, color = Color.Transparent)
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .border(borderStroke, shape)
            .background(Color.LightGray)
    ) {
        GlideImage(
            model = urlImage,
            contentDescription = "Cover Dispensa",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .border(borderStroke, shape)
            //.clip(shape),
        )
    }
}*/




/*

@OptIn(ExperimentalGlideComposeApi::class)
@Preview
@Composable
fun CustomGlideImage(
    urlImage : String? = null,
    shape : Shape = CircleShape,
    borderStroke: BorderStroke = BorderStroke(0.dp, color = Color.Transparent)
) {
    GlideImage(
        model = urlImage,
        contentDescription = "Cover Dispensa",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .border(borderStroke, shape)
        //.clip(shape),
    )
}


 */