plugins {
    kotlin("android")
}

withVersionCatalog { libs ->
    kotlin {
        jvmToolchain(libs.versions.jvm.get().toInt())
    }
}
