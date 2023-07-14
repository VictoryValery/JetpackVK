package com.victoryvalery.jetpackvk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.victoryvalery.jetpackvk.ui.MainViewModel
import com.victoryvalery.jetpackvk.ui.VkNewsMainScreen
import com.victoryvalery.jetpackvk.ui.theme.JetpackVKTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackVKTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    VkNewsMainScreen(viewModel)
                }
            }
        }
    }
}
