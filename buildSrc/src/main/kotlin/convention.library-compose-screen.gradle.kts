plugins {
    id("convention.library-compose")
    id("convention.serialization")
}

withVersionCatalog { libs ->
    dependencies {
        implementation(libs.androidx.navigation.compose)

        implementation(libs.kotlinx.serialization)

        implementation(libs.vice.core)
        implementation(libs.vice.nav)
    }
}
