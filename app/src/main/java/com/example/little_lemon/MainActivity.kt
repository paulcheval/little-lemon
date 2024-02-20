package com.example.little_lemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon.navigation.Navigation
import com.example.little_lemon.ui.theme.LittlelemonTheme

import com.example.little_lemon.ui.LittleLemonMenuViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LittlelemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LittleLemonApp()
                }
            }
        }
    }



}

@Composable
fun LittleLemonApp() {
    val viewModel: LittleLemonMenuViewModel = viewModel()

    viewModel.loadData()
    LittleLemonNavigation()
}


@Composable
fun LittleLemonNavigation() {
    LittlelemonTheme {
        val navController = rememberNavController()
        val context = LocalContext.current
        Navigation(navController, context)
    }
}

