plugins {
    id("codebase.android.feature")
    id("kotlin-kapt")
}

android {
    namespace = "com.genesys.feature.template"

    buildFeatures {
        dataBinding = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    // Epoxy
    implementation("com.airbnb.android:epoxy:5.1.4")
    kapt("com.airbnb.android:epoxy-processor:5.1.4")
}
