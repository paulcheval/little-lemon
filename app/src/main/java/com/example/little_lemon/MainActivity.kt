package com.example.little_lemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon.navigation.Navigation
import com.example.little_lemon.ui.OnboardingScreen
import com.example.little_lemon.ui.theme.LittlelemonTheme

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
                    MyNagivation()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MyNagivation() {
    LittlelemonTheme {
        val navController = rememberNavController()
        val context = LocalContext.current
        Navigation(navController, context)
    }
}