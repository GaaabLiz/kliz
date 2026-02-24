package io.github.gaaabliz.kliz.compose.common.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

@Stable
class ProfileField(
    val type: FieldType,
    private val initialValue: String?,
    val isImmutable : Boolean = false,
    val isTextArea : Boolean = false,
) {

    enum class FieldType(
        val label : String,
        val minLength: Int? = null,
        val maxLength: Int? = null,
        val maxLines : Int = 1,
        val leadingIcon : ImageVector,
        val inputType : KeyboardType = KeyboardType.Text,
    ) {
        ID(
            label = "ID",
            leadingIcon = Icons.Default.Badge,
        ),
        NAME(
            label = "Nome",
            minLength = 2,
            leadingIcon = Icons.Default.Person,
        ),
        SURNAME(
            label = "Cognome",
            minLength = 2,
            leadingIcon = Icons.Default.Person,
        ),
        PHONE(
            label = "Telefono",
            leadingIcon = Icons.Default.Phone,
            inputType = KeyboardType.Phone
        ),
        EMAIL(
            label = "Email",
            leadingIcon = Icons.Default.Email,
            inputType = KeyboardType.Email
        ),
        PASSWORD(
            label = "Password",
            leadingIcon = Icons.Default.Password,
            inputType = KeyboardType.Password
        ),
        PERSONAL_DESCRIPTION(
            label = "Descrizione personale",
            leadingIcon = Icons.Default.Description,
            maxLength = 500,
            maxLines = 5,
        ),
        COMPANY_NAME(
            label = "Nome azienda",
            leadingIcon = Icons.Default.Business,
        ),
        COMPANY_WEBSITE(
            label = "Sito azienda",
            leadingIcon = Icons.Default.Webhook,
        ),
        WORK_ROLE_DESCRIPTION(
            label = "Descrizione ruolo",
            leadingIcon = Icons.Default.Description,
            maxLength = 500,
            maxLines = 5,
        ),
    }


    private var fieldValue by mutableStateOf(initialValue)
    private var isInError by mutableStateOf(false)
    private var errorText by mutableStateOf("")
    private var isEnabled by mutableStateOf(true)

    fun handleValueChange(newValue: String, isInLength: Boolean) {
        if (isInLength) {
            fieldValue = newValue
            isInError = false
        }
    }

    fun isChanged() = initialValue != fieldValue
    fun restoreDefaultValue() = run { fieldValue = initialValue }
    fun update(newValue : String) = run { fieldValue = newValue }
    fun getValue() = fieldValue ?: ""
    fun getErrorStatus() = isInError
    fun setErrorStatus(isInError : Boolean, errorText : String? = null) = run {
        this.isInError = isInError
        this.errorText = errorText.toString()
    }
    fun getTextError() = errorText
    fun getEnabledStatus() = kotlin.run { if(isImmutable) false else isEnabled }
    fun setEnabledStatus(isEnabled : Boolean) = run { if(!isImmutable) this.isEnabled = isEnabled }

    fun checkAndSetError(condition : Boolean, description : String) {
        if(isImmutable) return
        if(condition) setErrorStatus(true, description)
        else setErrorStatus(false)
    }

    fun validate() {
        if(isImmutable) return
        when(type) {
            FieldType.ID -> {}
            FieldType.NAME -> {
                checkAndSetError(
                    condition = (fieldValue?.length ?: 0) > type.minLength!!,
                    description = "Il nome deve essere di almeno ${type.minLength} caratteri"
                )
            }
            FieldType.SURNAME -> {
                checkAndSetError(
                    condition = (fieldValue?.length ?: 0) > type.minLength!!,
                    description = "Il nome deve essere di almeno ${type.minLength} caratteri"
                )
            }
            FieldType.PHONE -> TODO()
            FieldType.EMAIL -> TODO()
            FieldType.PASSWORD -> TODO()
            FieldType.PERSONAL_DESCRIPTION -> TODO()
            FieldType.COMPANY_NAME -> TODO()
            FieldType.COMPANY_WEBSITE -> TODO()
            FieldType.WORK_ROLE_DESCRIPTION -> TODO()
        }
    }
}