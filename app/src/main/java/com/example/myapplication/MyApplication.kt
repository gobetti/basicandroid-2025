package com.example.myapplication

import android.app.Application
import com.example.core.di.ApplicationComponent
import com.example.core.di.ApplicationComponentProvider
import com.example.core.di.create

class MyApplication : Application(), ApplicationComponentProvider {
    override val component by lazy(LazyThreadSafetyMode.NONE) {
        ApplicationComponent::class.create(
            context = applicationContext
        )
    }
}
