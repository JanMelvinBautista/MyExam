package com.melvinbautista.exam.repository

import com.melvinbautista.exam.database.schema.UserSchema
import com.melvinbautista.exam.database.table.User
import jakarta.inject.Inject

class UserRepository @Inject constructor(private val userSchema: UserSchema) {

    suspend fun register(username: String, password: String): Boolean {
        return try {
            userSchema.register(User(username, password))
            true
        } catch (_: Exception) {
            false
        }
    }

    suspend fun login(username: String, password: String): Boolean {
        return userSchema.login(username, password) != null
    }
}
