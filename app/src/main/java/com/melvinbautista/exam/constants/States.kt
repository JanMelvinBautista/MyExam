package com.melvinbautista.exam.constants

object States {
    object Registered{
        const val SUCCESS = "User registered successfully."
        const val FAILED = "Username already exists"
    }

    object LoggedIn {
        const val SUCCESS = "Logged in successfully."
        const val FAILED = "Invalid username/password"
    }
}
