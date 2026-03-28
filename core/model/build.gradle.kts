plugins {
    id("codebase.android.library")
    id("org.jetbrains.kotlin.plugin.parcelize")
}

android {
    namespace = "com.genesys.core.model"
}

dependencies {
    // Moshi annotations
    implementation("com.squareup.moshi:moshi:1.15.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    // AndroidX annotations
    implementation("androidx.core:core-ktx:1.16.0")
}
