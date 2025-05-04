plugins {
    id("convention.library-compose-screen")
    id("convention.library-inject")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.feature.mylist"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.di)
    implementation(projects.core.network) // this is only here to access ApplicationComponent...

    implementation(libs.sqldelight)
    implementation(libs.sqldelight.coroutines)
}
