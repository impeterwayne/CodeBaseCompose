
plugins {
    id("codebase.android.library")
    id("codebase.android.compose")
    id("kotlin-parcelize")
    alias(libs.plugins.kotlinSerialization)
}


android {
    namespace = "com.genesys.core.navigation"
}

dependencies {
    api(libs.navigation3Runtime)
    api(libs.navigation3Ui)
    
    // It's common for navigation to depend on core types or compose
    implementation(libs.composeFoundation)
    implementation(libs.composeRuntime)
    implementation(libs.kotlinxSerializationJson)
}
