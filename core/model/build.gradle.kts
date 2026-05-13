
plugins {
    id("codebase.android.library")
    alias(libs.plugins.kotlinParcelize)
}


android {
    namespace = "com.genesys.core.model"
}

dependencies {
    // Gson annotations
    implementation(libs.gson)

    // AndroidX annotations
    implementation(libs.androidxCoreKtx)
}
