import androidx.compose.ui.graphics.vector.ImageVector

data class AppSettingInfo(
    val settingId : String,
    val settingName : String,
    val settingDescription : String,
    val icon : ImageVector,
    val type : AppSettingType
)
