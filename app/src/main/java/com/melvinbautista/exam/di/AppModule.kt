package com.melvinbautista.exam.di

import android.content.Context
import androidx.room.Room
import com.melvinbautista.exam.database.DBExam
import com.melvinbautista.exam.database.schema.UserSchema
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DBExam =
        Room.databaseBuilder(context, DBExam::class.java, "Database").build()

    @Provides
    fun provideUserSchema(db: DBExam): UserSchema = db.userSchema()
}
