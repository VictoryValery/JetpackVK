package com.victoryvalery.jetpackvk.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victoryvalery.jetpackvk.R
import com.victoryvalery.jetpackvk.ui.NavigationItem.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VkNewsMainScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val fabIsVisible = remember { mutableStateOf(true) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            if (fabIsVisible.value)
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = {
                        scope.launch {
                            val action = snackbarHostState.showSnackbar(
                                message = "FAB snackbar message",
                                actionLabel = "Hide FAB",
                                duration = SnackbarDuration.Long
                            )
                            if (action == SnackbarResult.ActionPerformed)
                                fabIsVisible.value = false
                        }
                    },
                ) {
                    Icon(Icons.Filled.Favorite, stringResource(R.string.main_floating_action_button))
                }
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .height(74.dp)
            ) {
                var selectedItem by remember { mutableStateOf(0) }
                val items = listOf(Home, Favourite, Profile)
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = stringResource(item.titleResId)) },
                        label = { Text(stringResource(item.titleResId)) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                    )
                }
            }
        }
    )
    {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text("скэффолд")
        }
    }
}