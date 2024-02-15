package com.example.little_lemon.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon.ui.HomeScreen
import com.example.little_lemon.ui.OnboardingScreen
import com.example.little_lemon.ui.ProfileScreen


@Composable
fun Navigation(navController: NavHostController = rememberNavController(),  context: Context) {

    NavHost(
        navController = navController,
        startDestination = determineStartingScreen(context).route
    ) {
        composable(Home.route) {
            HomeScreen(navController)
        }
        composable(Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(Profile.route) {
            ProfileScreen(navController)
        }
    }
}

private fun determineStartingScreen(context: Context): Destinations {
    if (isUserLoggedIn(context)) {
        return Home
    }
    return Onboarding
}

private fun isUserLoggedIn(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences(
        "LittleLemon.prefs",
        Context.MODE_PRIVATE
    )
    return sharedPreferences.getBoolean("clientLoggedIn", false)
}