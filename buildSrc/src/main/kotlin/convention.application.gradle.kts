import com.android.build.gradle.AppExtension

plugins {
    id("com.android.application")
    id("convention.android-base")
    id("convention.kotlin")
}

withVersionCatalog { libs ->
    configure<AppExtension> {
        defaultConfig {
            targetSdk = libs.versions.targetSdk.get().toInt()
        }
    }
}
