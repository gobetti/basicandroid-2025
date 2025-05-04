package com.example.core.data.di

import android.content.Context
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.core.base.ApplicationScope
import com.example.core.data.Database
import com.example.myapplication.Product
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import me.tatarka.inject.annotations.Provides

interface DatabaseComponent : JsonObjectAdapterComponent {
    @Provides @ApplicationScope
    fun provideDatabase(
        context: Context,
        jsonObjectAdapter: ColumnAdapter<JsonObject, String>
    ): Database = Database(
        AndroidSqliteDriver(Database.Schema, context, "test.db"),
        ProductAdapter = Product.Adapter(
            productJsonAdapter = jsonObjectAdapter
        )
    )
}

interface JsonObjectAdapterComponent : JsonComponent {
    @Provides @ApplicationScope
    fun provideJsonObjectAdapter(json: Json) = object : ColumnAdapter<JsonObject, String> {
        override fun decode(databaseValue: String) = json.parseToJsonElement(databaseValue).jsonObject

        override fun encode(value: JsonObject): String = value.toString()
    }
}
