plugins {
    id("convention.library-compose")
    id("convention.library-inject")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.feature.home"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.di)
    implementation(projects.core.network)

    implementation(libs.androidx.datastore)

    implementation(libs.ktor.client.core)

    implementation(libs.sqldelight)
    implementation(libs.sqldelight.coroutines)

    implementation(libs.vice.core)
    implementation(libs.vice.nav)
}
