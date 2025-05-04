plugins {
    id("convention.library-inject")
}

android {
    namespace = "com.example.core.di"
}

dependencies {
    implementation(projects.core.base)
    implementation(projects.core.data)
    implementation(projects.core.network)

    implementation(libs.androidx.datastore)
    implementation(libs.ktor.client.core)
}
