plugins {
    id("convention.library-compose")
    id("convention.hilt")
}

withVersionCatalog { libs ->
    dependencies {
        implementation(libs.androidx.hilt.navigation.compose)
        implementation(libs.hilt)
        ksp(libs.hilt.compiler)
    }
}
