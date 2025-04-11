plugins {
    id("convention.library")
    id("convention.serialization")
}

withVersionCatalog { libs ->
    dependencies {
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.material3)
        implementation(libs.androidx.navigation.compose)

        implementation(libs.androidx.ui.tooling.preview)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)

        implementation(libs.kotlinx.serialization)
    }
}
