package com.melvinbautista.exam.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.melvinbautista.exam.navigations.NavigationScreens
import com.melvinbautista.exam.ui.HomeScreen
import com.melvinbautista.exam.ui.LoginScreen
import com.melvinbautista.exam.ui.RegisterScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = NavigationScreens.Login.route
            ) {
                composable(NavigationScreens.Login.route) {
                    LoginScreen(navController)
                }
                composable(NavigationScreens.Register.route) {
                    RegisterScreen(navController)
                }
                composable(NavigationScreens.Home.route) {
                    HomeScreen(navController)
                }
            }
        }
    }
}
