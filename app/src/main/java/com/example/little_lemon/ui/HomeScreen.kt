package com.example.little_lemon.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.little_lemon.navigation.Profile

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        Text(text = "Home Screen")
        Button(onClick = { navController.navigate(Profile.route) }) {
            Text(text = "Click for Profile")
        }
        
    }
}

/*
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}*/
