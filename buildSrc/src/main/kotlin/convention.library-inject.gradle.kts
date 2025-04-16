plugins {
    id("convention.library")
    id("convention.inject")
}

withVersionCatalog { libs ->
    dependencies {
        implementation(libs.kotlinInject.runtime)
        ksp(libs.kotlinInject.compiler)
    }
}
