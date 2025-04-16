package com.example.myapplication

import android.app.Application
import com.example.core.data.di.DataStoreComponent
import com.example.core.data.di.DatabaseComponent
import com.example.core.data.di.JsonComponent
import com.example.core.data.di.JsonObjectAdapterComponent
import com.example.core.data.di.create
import com.example.core.di.ApplicationComponent
import com.example.core.di.ApplicationComponentProvider
import com.example.core.di.create
import com.example.core.network.NetworkComponent
import com.example.core.network.create

class MyApplication : Application(), ApplicationComponentProvider {
    override val component by lazy(LazyThreadSafetyMode.NONE) {
        ApplicationComponent::class.create(
            context = applicationContext,
            databaseComponent = DatabaseComponent::class.create(
                context = applicationContext,
                jsonObjectAdapterComponent = JsonObjectAdapterComponent::class.create(
                    jsonComponent = JsonComponent::class.create()
                )
            ),
            dataStoreComponent = DataStoreComponent::class.create(applicationContext),
            networkComponent = NetworkComponent::class.create()
        )
    }
}
