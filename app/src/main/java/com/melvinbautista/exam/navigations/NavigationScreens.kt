package com.melvinbautista.exam.navigations

sealed class NavigationScreens(val route: String) {
    object Home : NavigationScreens(HOME)
    object Login : NavigationScreens(LOGIN)
    object Register : NavigationScreens(REGISTER)

    companion object {
        const val HOME = "home"
        const val LOGIN = "login"
        const val REGISTER = "register"
    }
}
