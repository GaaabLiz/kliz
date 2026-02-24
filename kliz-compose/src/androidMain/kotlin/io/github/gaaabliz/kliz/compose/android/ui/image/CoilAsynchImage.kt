package io.github.gaaabliz.kliz.compose.android.ui.image

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun CustomCoilImage(
    urlImage : String? = null,
    size : Dp = 100.dp,
    shape : Shape = CircleShape,
    borderStroke: BorderStroke = BorderStroke(0.dp, color = Color.Transparent)
) {

    val builder = ImageRequest.Builder(LocalContext.current)
        .data(data = urlImage)
        .build()

    val painter = rememberAsyncImagePainter(builder)


    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(size)
            .height(size)
            .border(borderStroke, shape)
            .clip(shape),
    )

}

@Composable
fun AdvancedCoilImage(
    urlImage : String? = null,
    height : Dp = 100.dp,
    width : Dp = 100.dp,
    shape : Shape = CircleShape,
    errorPlaceholder : Int,
    borderStroke: BorderStroke = BorderStroke(0.dp, color = Color.Transparent),
    onImageSuccess : () -> Unit = {},
    onImageError : () -> Unit = {},
    onImageLoading : () -> Unit = {},
) {

    val builder = ImageRequest.Builder(LocalContext.current)
        .data(data = urlImage)
        .error(errorPlaceholder)
        .crossfade(500)
        .build()

    val painter = rememberAsyncImagePainter(
        model = builder,
        onState = { state ->
            when (state) {
                is AsyncImagePainter.State.Loading -> {
                    onImageLoading()
                }
                is AsyncImagePainter.State.Success -> {
                    onImageSuccess()
                }
                is AsyncImagePainter.State.Error -> {
                    onImageError()
                }
                else -> {}
            }
        }
    )

    val painterState = painter.state

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(width)
            .height(height)
            .border(borderStroke, shape)
            .clip(shape),
    )
    if(painterState is AsyncImagePainter.State.Loading) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
        )
    }
}



