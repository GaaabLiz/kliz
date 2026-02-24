package io.github.gaaabliz.kliz.compose.common.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

sealed class ProfileFieldType(
    open var label : String,
    open var minLength: Int? = null,
    open var maxLength: Int? = null,
    open var maxLines : Int = 1,
    open var leadingIcon : ImageVector,
    open var inputType : KeyboardType = KeyboardType.Text,
) {

    data class ID(
        override var label: String = "Id",
        override var minLength: Int? = null,
        override var maxLength: Int? = null,
        override var maxLines: Int = 1,
        override var leadingIcon: ImageVector = Icons.Default.Badge,
        override var inputType: KeyboardType = KeyboardType.Text,
    ) : ProfileFieldType(
        label = label,
        minLength = minLength,
        leadingIcon = leadingIcon,
        maxLength = maxLength,
        maxLines = maxLines,
        inputType = inputType,
    )

    data class NAME(
        override var label: String = "Nome",
        override var minLength: Int? = 2,
        override var maxLength: Int? = 50,
        override var maxLines: Int = 1,
        override var leadingIcon: ImageVector = Icons.Default.Person,
        override var inputType: KeyboardType = KeyboardType.Text,
    ) : ProfileFieldType(
        label = label,
        minLength = minLength,
        leadingIcon = leadingIcon,
        maxLength = maxLength,
        maxLines = maxLines,
        inputType = inputType,
    )

    data class SURNAME(
        override var label: String = "Cognome",
        override var minLength: Int? = 2,
        override var maxLength: Int? = 50,
        override var maxLines: Int = 1,
        override var leadingIcon: ImageVector = Icons.Default.Person,
        override var inputType: KeyboardType = KeyboardType.Text,
    ) : ProfileFieldType(
        label = label,
        minLength = minLength,
        leadingIcon = leadingIcon,
        maxLength = maxLength,
        maxLines = maxLines,
        inputType = inputType,
    )

    data class PHONE(
        override var label: String = "Telefono",
        override var minLength: Int? = 10,
        override var maxLength: Int? = null,
        override var maxLines: Int = 1,
        override var leadingIcon: ImageVector = Icons.Default.Phone,
        override var inputType: KeyboardType = KeyboardType.Phone,
    ) : ProfileFieldType(
        label = label,
        minLength = minLength,
        leadingIcon = leadingIcon,
        maxLength = maxLength,
        maxLines = maxLines,
        inputType = inputType,
    )

    data class EMAIL(
        override var label: String = "Email",
        override var minLength: Int? = 5,
        override var maxLength: Int? = null,
        override var maxLines: Int = 1,
        override var leadingIcon: ImageVector = Icons.Default.Email,
        override var inputType: KeyboardType = KeyboardType.Email,
    ) : ProfileFieldType(
        label = label,
        minLength = minLength,
        leadingIcon = leadingIcon,
        maxLength = maxLength,
        maxLines = maxLines,
        inputType = inputType,
    )

    data class PASSWORD(
        override var label: String = "Password",
        override var minLength: Int? = 5,
        override var maxLength: Int? = null,
        override var maxLines: Int = 1,
        override var leadingIcon: ImageVector = Icons.Default.Password,
        override var inputType: KeyboardType = KeyboardType.Password,
    ) : ProfileFieldType(
        label = label,
        minLength = minLength,
        leadingIcon = leadingIcon,
        maxLength = maxLength,
        maxLines = maxLines,
        inputType = inputType,
    )
}
