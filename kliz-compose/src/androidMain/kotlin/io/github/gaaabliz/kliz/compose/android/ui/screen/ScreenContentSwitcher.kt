package io.github.gaaabliz.kliz.compose.android.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun <T> ScreenSectionContentSwitcher(
    isRefreshing : Boolean = false,
    operationActive : Boolean = false,
    onRefresh : () -> Unit = {},
    list : List<T>,
    contentWhenIsRefreshingEmpty : @Composable () -> Unit,
    contentWhenIsNotRefreshingEmpty : @Composable () -> Unit,
    contentWhenIsNotEmpty : @Composable () -> Unit,
) {

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        swipeEnabled = !operationActive,
        refreshTriggerDistance = 100.dp,
        onRefresh = onRefresh,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        if(list.isEmpty()) {
            if(isRefreshing) {
                contentWhenIsRefreshingEmpty()
            } else {
                contentWhenIsNotRefreshingEmpty()
            }
        } else {
            contentWhenIsNotEmpty()
        }
    }
}