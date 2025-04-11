plugins {
    id("convention.library")
    id("convention.hilt")
}

android {
    namespace = "com.example.core.network"
}

dependencies {
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.serialization.kotlinx.json)
}
