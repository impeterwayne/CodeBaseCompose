
plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
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
    implementation(platform(libs.okhttpBom))
    implementation(libs.okhttp)
    implementation(libs.loggingInterceptor)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2KotlinCoroutinesAdapter)
    implementation(libs.converterGson)

    // Sandwich
    implementation(libs.sandwich)
    implementation(libs.sandwichRetrofit)

    // Gson
    implementation(libs.gson)
}
