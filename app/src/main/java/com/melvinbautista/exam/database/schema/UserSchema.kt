package com.melvinbautista.exam.database.schema

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.melvinbautista.exam.database.table.User

@Dao
interface UserSchema {
    @Insert()
    suspend fun register(user: User)

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): User?

}
