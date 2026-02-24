package io.github.gaaabliz.kliz.compose.common.ui.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.gaaabliz.kliz.compose.common.model.ProfileField

@Composable
fun rememberProfileField(
    type: ProfileField.FieldType,
    initialValue: String?,
    isImmutable : Boolean = false,
    isTextArea : Boolean = false,
) = remember {
    ProfileField(
        type = type,
        initialValue = initialValue,
        isImmutable = isImmutable,
        isTextArea = isTextArea,
    )
}


@Composable
fun ProfileFieldBox(
    field : ProfileField,
    contentTextArea: @Composable (field : ProfileField) -> Unit,
    contentTextField: @Composable (field : ProfileField) -> Unit,
) {
    if(field.isTextArea) {
        contentTextArea(field)
    } else {
        contentTextField(field)
    }
}
