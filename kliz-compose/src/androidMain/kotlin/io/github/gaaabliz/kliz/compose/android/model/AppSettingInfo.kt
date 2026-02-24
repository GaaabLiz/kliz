import androidx.compose.ui.graphics.vector.ImageVector
import io.github.gaaabliz.kliz.android.model.AppSettingType

data class AppSettingInfo(
    val settingId : String,
    val settingName : String,
    val settingDescription : String,
    val icon : ImageVector,
    val type : AppSettingType
)
