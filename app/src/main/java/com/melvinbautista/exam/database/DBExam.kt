package com.melvinbautista.exam.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.melvinbautista.exam.database.schema.UserSchema
import com.melvinbautista.exam.database.table.User

@Database(entities = [User::class], version = 1)
abstract class DBExam : RoomDatabase() {
    abstract fun userSchema(): UserSchema
}
