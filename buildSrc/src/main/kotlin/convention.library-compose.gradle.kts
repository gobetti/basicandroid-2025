plugins {
    id("convention.library")
}

withVersionCatalog { libs ->
    dependencies {
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.material3)

        implementation(libs.androidx.ui.tooling.preview)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
    }
}
