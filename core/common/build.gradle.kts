plugins {
    id("codebase.android.library")
}

android {
    namespace = "com.genesys.core.common"
}

dependencies {
    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // AndroidX
    implementation("androidx.core:core-ktx:1.16.0")
    api("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // ImmersionBar
    implementation("com.geyifeng.immersionbar:immersionbar:3.2.2")
    implementation("com.geyifeng.immersionbar:immersionbar-ktx:3.2.2")
    implementation("com.geyifeng.immersionbar:immersionbar-components:3.2.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Gson (used by GsonExt)
    implementation("com.google.code.gson:gson:2.13.1")

    // Lifecycle runtime (used by FlowExt)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
}
