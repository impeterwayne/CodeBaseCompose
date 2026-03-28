plugins {
    id("codebase.android.library")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.genesys.core.ui"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    // Core modules
    implementation(project(":core:common"))

    // AndroidX
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    api("com.google.android.material:material:1.12.0")
    api("androidx.constraintlayout:constraintlayout:2.2.1")

    // SDP / SSP
    implementation("com.intuit.sdp:sdp-android:1.1.1")
    implementation("com.intuit.ssp:ssp-android:1.1.1")

    // ShapeView
    implementation("com.github.getActivity:ShapeView:9.8")
    implementation("com.github.getActivity:ShapeDrawable:3.3")

    // Lottie
    implementation("com.airbnb.android:lottie:6.6.7")

    // Epoxy
    api("com.airbnb.android:epoxy:5.1.4")
    kapt("com.airbnb.android:epoxy-processor:5.1.4")
}
