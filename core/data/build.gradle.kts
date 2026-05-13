
plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
}


android {
    namespace = "com.genesys.core.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:common"))

    // MMKV
    implementation(libs.mmkv)

    // Coroutines
    implementation(libs.kotlinxCoroutinesCore)
    implementation(libs.kotlinxCoroutinesAndroid)

    // Sandwich
    implementation(libs.sandwich)
    implementation(libs.sandwichRetrofit)
}
