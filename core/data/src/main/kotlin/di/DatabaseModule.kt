package com.example.core.data.di

import android.content.Context
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.core.data.Database
import com.example.myapplication.Product
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope
annotation class DatabaseScope

@Component
abstract class DatabaseComponent(
    private val context: Context,
    @Component val jsonObjectAdapterComponent: JsonObjectAdapterComponent
) {
    @Provides
    fun provideDatabase(
        jsonObjectAdapter: ColumnAdapter<JsonObject, String>
    ): Database = Database(
        AndroidSqliteDriver(Database.Schema, context, "test.db"),
        ProductAdapter = Product.Adapter(
            productJsonAdapter = jsonObjectAdapter
        )
    )
}

@DatabaseScope
@Component
abstract class JsonObjectAdapterComponent(
    @Component val jsonComponent: JsonComponent
) {
    @Provides
    fun provideJsonObjectAdapter(json: Json) = object : ColumnAdapter<JsonObject, String> {
        override fun decode(databaseValue: String) = json.parseToJsonElement(databaseValue).jsonObject

        override fun encode(value: JsonObject): String = value.toString()
    }
}
