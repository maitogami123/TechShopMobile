package com.vi.techshopmobile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vi.techshopmobile.network.ConnectionState
import com.vi.techshopmobile.network.connectivityState
import com.vi.techshopmobile.presentation.navgraph.NavGraph
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.EventBus
import com.vi.techshopmobile.util.ReadJSONFromAssets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

val LocalToken = compositionLocalOf<String> {
    error("No LocalToken provided")
}

val LocalProvinces = compositionLocalOf<String> {
    error("No LocalProvinces provided")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = ReadJSONFromAssets(context = baseContext, "provinces.json")
//        WindowCompat.setDecorFitsSystemWindows(window, false);

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        };

        setContent {
            CompositionLocalProvider(
                LocalToken provides viewModel.accessToken,
                LocalProvinces provides data,
            ) {
                TechShopMobileTheme {
                    val lifecycle = LocalLifecycleOwner.current.lifecycle
                    LaunchedEffect(key1 = lifecycle) {
                        repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                            EventBus.events.collect { event ->
                                if (event is Event.Toast) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        event.message,
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                            }
                        }
                    }
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val isSystemInDarkMode = isSystemInDarkTheme();
                        val systemController = rememberSystemUiController()

                        SideEffect {
                            systemController.setSystemBarsColor(
                                color = Color.Transparent,
                                darkIcons = !isSystemInDarkMode
                            )
                        }
                            Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                                val startDestination = viewModel.startDestination
                                NavGraph(startDestination = startDestination)
                            }
                    }
                }
            }
        }
    }
}