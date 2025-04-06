package com.example.myapplication.di

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.myapplication.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): Database = Database(
        AndroidSqliteDriver(Database.Schema, context, "test.db")
    )
}
