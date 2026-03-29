import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.library")
    alias(libs.plugins.kotlinParcelize)
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.core.model"
}

dependencies {
    // Moshi annotations
    implementation(deps.moshi)
    implementation(deps.moshiKotlin)

    // AndroidX annotations
    implementation(deps.androidxCoreKtx)
}
