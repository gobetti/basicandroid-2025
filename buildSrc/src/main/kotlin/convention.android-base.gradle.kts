import com.android.build.gradle.BaseExtension

withVersionCatalog { libs ->
    configure<BaseExtension> {
        compileSdkVersion(libs.versions.compileSdk.get().toInt())
        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
            multiDexEnabled = true
        }
    }
}
