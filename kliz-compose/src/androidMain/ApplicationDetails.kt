import io.github.gaaabliz.kliz.android.model.NotificChannelInfo
import io.github.gaaabliz.kliz.common.base.emptyString
import io.github.gaaabliz.kliz.common.model.ProjectAuthor


interface ApplicationDetails {
    val appLogTag: String get() = "tag"
    val appVersion: String get() = emptyString
    val appName : String get() = emptyString
    val appDescription : String get() = emptyString
    val appIcon : Int get() = 0
    val authors : List<ProjectAuthor> get() = emptyList()
    val isDebug: Boolean get() = false
    val notificationChannels : List<NotificChannelInfo> get() = emptyList()
    val settings : List<AppSettingInfo> get() = emptyList()
    val settingsDataStoreFileName : String get() = "${appName}_settings.json"
}