plugins {
    id("convention.library-inject")
    id("convention.serialization")
    id("kotlin-parcelize")
    alias(libs.plugins.sqldelight)
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.example.core.data")
            dialect("app.cash.sqldelight:sqlite-3-38-dialect:${libs.versions.sqldelight.get()}")
        }
    }
}

android {
    namespace = "com.example.core.data"
}

dependencies {
    implementation(libs.androidx.datastore)

    implementation(libs.kotlinx.serialization)

    implementation(libs.sqldelight)
    implementation(libs.sqldelight.coroutines)
}

