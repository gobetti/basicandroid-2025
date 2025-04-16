package com.example.core.di

import android.content.Context
import com.example.core.data.di.DataStoreComponent
import com.example.core.data.di.DatabaseComponent
import com.example.core.network.NetworkComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Component @ApplicationScope
abstract class ApplicationComponent(
    @get:Provides val context: Context,
    @Component val databaseComponent: DatabaseComponent,
    @Component val dataStoreComponent: DataStoreComponent,
    @Component val networkComponent: NetworkComponent
)

interface ApplicationComponentProvider {
    val component: ApplicationComponent
}

val Context.applicationComponent get() = (applicationContext as ApplicationComponentProvider).component
