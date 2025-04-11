plugins {
    id("convention.library-compose-hilt")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.feature.home"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.network)

    implementation(libs.androidx.datastore)

    implementation(libs.ktor.client.core)

    implementation(libs.sqldelight)
    implementation(libs.sqldelight.coroutines)

    testImplementation(libs.test.compose.junit4)
    testImplementation(libs.test.hilt)
    testImplementation(libs.test.robolectric)
}
