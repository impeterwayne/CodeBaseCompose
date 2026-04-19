import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.library")
    id("codebase.android.compose")
    id("kotlin-parcelize")
    alias(libs.plugins.kotlinSerialization)
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.core.navigation"
}

dependencies {
    api(deps.navigation3Runtime)
    api(deps.navigation3Ui)
    
    // It's common for navigation to depend on core types or compose
    implementation(deps.composeFoundation)
    implementation(deps.composeRuntime)
    implementation(deps.kotlinxSerializationJson)
}
