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
    implementation("com.tencent:mmkv:1.3.14")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Sandwich
    implementation("com.github.skydoves:sandwich:2.0.8")
    implementation("com.github.skydoves:sandwich-retrofit:2.0.8")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")
}
