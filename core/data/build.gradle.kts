plugins {
    id("convention.library")
    id("convention.hilt")
    id("convention.serialization")
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

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.kotlinx.serialization)

    implementation(libs.sqldelight)
    implementation(libs.sqldelight.coroutines)
}

