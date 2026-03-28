plugins {
    id("codebase.android.library")
    id("kotlin-kapt")
}

android {
    namespace = "com.genesys.core.common"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // AndroidX
    implementation("androidx.core:core-ktx:1.16.0")
    api("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.activity:activity:1.10.1")
    api("androidx.fragment:fragment-ktx:1.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // ImmersionBar
    implementation("com.geyifeng.immersionbar:immersionbar:3.2.2")
    implementation("com.geyifeng.immersionbar:immersionbar-ktx:3.2.2")
    implementation("com.geyifeng.immersionbar:immersionbar-components:3.2.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Glide (used by ImageViewExt)
    implementation("com.github.bumptech.glide:glide:4.15.1")

    // Gson (used by GsonExt)
    implementation("com.google.code.gson:gson:2.13.1")

    // Lifecycle runtime (used by FlowExt)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
}
