
plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
}


android {
    namespace = "com.genesys.core.domain"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    // Coroutines
    implementation(libs.kotlinxCoroutinesCore)
    implementation(libs.kotlinxCoroutinesAndroid)

    // AndroidX annotations
    implementation(libs.androidxCoreKtx)
}
