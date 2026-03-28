plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.genesys.core.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://ai-service.backendvn.com/\"")
    }
}

dependencies {
    implementation(project(":core:model"))

    // OkHttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Sandwich
    implementation("com.github.skydoves:sandwich:2.0.8")
    implementation("com.github.skydoves:sandwich-retrofit:2.0.8")

    // Moshi
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.1")
    implementation("com.squareup.moshi:moshi:1.15.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    implementation("com.squareup.moshi:moshi-adapters:1.15.1")
}
