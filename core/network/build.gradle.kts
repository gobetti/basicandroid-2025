plugins {
    id("convention.library-inject")
}

android {
    namespace = "com.example.core.network"
}

dependencies {
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.serialization.kotlinx.json)
}
