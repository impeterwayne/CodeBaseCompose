package com.genesys.codebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.codebase.navigation.AppShell
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.immersionBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure ImmersionBar before Compose content
        immersionBar {
            transparentBar()
            statusBarDarkFont(true)
            fitsSystemWindows(false)
            hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
            navigationBarEnable(false)
        }

        enableEdgeToEdge()

        setContent {
            GenesysTheme {
                AppShell()
            }
        }
    }
}
