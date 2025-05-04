package com.example.core.di

import android.content.Context
import com.example.core.base.ApplicationScope
import com.example.core.data.di.DataStoreComponent
import com.example.core.data.di.DatabaseComponent
import com.example.core.network.NetworkComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component @ApplicationScope
abstract class ApplicationComponent(
    @get:Provides override val context: Context
) : DatabaseComponent,
    DataStoreComponent,
    NetworkComponent

interface ApplicationComponentProvider {
    val component: ApplicationComponent
}

val Context.applicationComponent get() = (applicationContext as ApplicationComponentProvider).component
