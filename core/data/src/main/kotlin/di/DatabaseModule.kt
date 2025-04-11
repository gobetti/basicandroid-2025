package com.example.core.data.di

import android.content.Context
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.core.data.Database
import com.example.myapplication.Product
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        jsonObjectAdapter: ColumnAdapter<JsonObject, String>
    ): Database = Database(
        AndroidSqliteDriver(Database.Schema, context, "test.db"),
        ProductAdapter = Product.Adapter(
            productJsonAdapter = jsonObjectAdapter
        )
    )
}

@Module
@InstallIn(SingletonComponent::class)
object JsonObjectAdapterModule {
    @Provides
    fun provideJsonObjectAdapter(json: Json) = object : ColumnAdapter<JsonObject, String> {
        override fun decode(databaseValue: String) = json.parseToJsonElement(databaseValue).jsonObject

        override fun encode(value: JsonObject): String = value.toString()
    }
}
