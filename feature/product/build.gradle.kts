plugins {
    id("convention.library-compose-screen")
    id("convention.library-inject")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.feature.product"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.di)

    implementation(libs.sqldelight)
    implementation(libs.sqldelight.coroutines)
}
