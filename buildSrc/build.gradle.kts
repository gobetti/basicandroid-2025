plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.com.android.tools.build.gradle)
    implementation(libs.com.google.devtools.ksp.gradle.plugin)
    implementation(libs.org.jetbrains.kotlin.android.gradle.plugin)
    implementation(libs.org.jetbrains.kotlin.serialization.plugin)
    // workaround for https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
