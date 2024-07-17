package com.muratdayan.foodsappwithmultimodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muratdayan.foodsappwithmultimodule.navigation.NavigationSubGraphs
import com.muratdayan.foodsappwithmultimodule.navigation.RecipeNavigation
import com.muratdayan.foodsappwithmultimodule.ui.theme.FoodsAppWithMultiModuleTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationSubGraphs: NavigationSubGraphs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodsAppWithMultiModuleTheme {
                Surface(
                    modifier = Modifier.safeContentPadding()
                ) {
                    RecipeNavigation(navigationSubGraphs = navigationSubGraphs)
                }
            }
        }
    }
}