
plugins {
    id("codebase.android.library")
}


android {
    namespace = "com.genesys.core.common"
}

dependencies {
    // AndroidX
    implementation(libs.androidxCoreKtx)
    api(libs.androidxAppcompat)
    implementation(libs.lifecycleViewmodelKtx)

    // ImmersionBar
    implementation(libs.immersionbar)
    implementation(libs.immersionbarKtx)
    implementation(libs.immersionbarComponents)

    // Coroutines
    implementation(libs.kotlinxCoroutinesCore)
    implementation(libs.kotlinxCoroutinesAndroid)

    // Gson (used by GsonExt)
    implementation(libs.gson)

    // Lifecycle runtime (used by FlowExt)
    implementation(libs.androidxLifecycleRuntimeKtx)

    // Orbit MVI
    api(libs.orbitViewmodel)
}
