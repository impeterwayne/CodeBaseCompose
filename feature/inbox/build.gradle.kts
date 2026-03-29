plugins {
    id("codebase.android.feature")
}

android {
    namespace = "com.genesys.feature.inbox"
}

dependencies {
    implementation("org.orbit-mvi:orbit-viewmodel:10.0.0")
    implementation("org.orbit-mvi:orbit-compose:10.0.0")
}
