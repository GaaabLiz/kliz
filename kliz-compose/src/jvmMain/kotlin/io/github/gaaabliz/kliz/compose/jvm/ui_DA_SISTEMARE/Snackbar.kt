package io.github.gaaabliz.kliz.compose.jvm.ui_DA_SISTEMARE


/*
@Composable
fun SnackbarContainerClassic(
    hostState: SnackbarHostState,
    data : SnackbarData
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(3.dp, data.type.colorPrimary),
        modifier = Modifier.wrapContentSize(),
        backgroundColor = data.type.colorBackground
    ) {
        Column(
            modifier = Modifier.padding(15.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.weight(weight = 1F, fill = false),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    when(data.type.innerBoxType) {
                        SnackbarInnerBoxType.ICON -> {
                            if(data.type.icon != null)
                                Icon(imageVector = data.type.icon, contentDescription = "", tint = data.type.colorPrimary)
                        }
                        SnackbarInnerBoxType.CIRCULAR_PROGRESS_INDEF -> {
                            CircularProgressIndicator(
                                color = data.type.colorPrimary,
                                modifier = Modifier.size(30.dp),
                                strokeWidth = 3.dp
                            )
                        }
                    }
                }
                Spacer(Modifier.width(10.dp))
                Column(
                    modifier = Modifier.weight(weight = 2F, fill = false),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = data.title,
                        color = Color.Black,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = data.title,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Justify
                    )
                    if(data.type.hasInnerProgressBar) {
                        Spacer(Modifier.height(10.dp))
                        LinearProgressIndicator(
                            color = data.type.colorPrimary,
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = Color(0xFFB0D0FB),
                        )
                    }
                }
                if(data.type.durationDelay == null) {
                    Spacer(Modifier.width(40.dp))
                    IconButton(onClick = {
                        hostState.currentSnackbarData?.dismiss()
                    }) {
                        Icon(tint = data.type.colorPrimary, imageVector = Icons.Filled.Close, contentDescription = "",)
                    }
                }
            }
        }
    }
}

@Composable
fun SnackbarContainer(
    isVisible : Boolean,
    data : SnackbarData,
    onClose : () -> Unit
) {
    if(isVisible) {
        Card(
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(3.dp, data.type.colorPrimary),
            modifier = Modifier.wrapContentSize(),
            elevation = 5.dp,
            backgroundColor = data.type.colorBackground
        ) {
            Column(
                modifier = Modifier.padding(15.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier.weight(weight = 1F, fill = false),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        when(data.type.innerBoxType) {
                            SnackbarInnerBoxType.ICON -> {
                                if(data.type.icon != null)
                                    Icon(imageVector = data.type.icon, contentDescription = "", tint = data.type.colorPrimary)
                            }
                            SnackbarInnerBoxType.CIRCULAR_PROGRESS_INDEF -> {
                                CircularProgressIndicator(
                                    color = data.type.colorPrimary,
                                    modifier = Modifier.size(30.dp),
                                    strokeWidth = 3.dp
                                )
                            }
                        }
                    }
                    Spacer(Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.weight(weight = 2F, fill = false),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = data.title,
                            color = Color.Black,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Justify
                        )
                        Text(
                            text = data.title,
                            color = Color.DarkGray,
                            style = MaterialTheme.typography.body2,
                            textAlign = TextAlign.Justify
                        )
                        if(data.type.hasInnerProgressBar) {
                            Spacer(Modifier.height(10.dp))
                            LinearProgressIndicator(
                                color = data.type.colorPrimary,
                                modifier = Modifier.fillMaxWidth(),
                                backgroundColor = Color(0xFFB0D0FB),
                            )
                        }
                    }
                    if(data.type.durationDelay == null) {
                        Spacer(Modifier.width(40.dp))
                        IconButton(onClick = { onClose() }) { Icon(tint = data.type.colorPrimary, imageVector = Icons.Filled.Close, contentDescription = "",) }
                    }
                }
            }
        }
    }

}

 */