plugins {
    id("convention.library-compose-hilt")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.feature.mylist"
}

dependencies {
    implementation(projects.core.data)

    implementation(libs.sqldelight)
    implementation(libs.sqldelight.coroutines)
}
