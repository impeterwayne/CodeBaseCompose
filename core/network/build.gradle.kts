import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
}

val deps = the<LibrariesForLibs>()

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
    implementation(platform(deps.okhttpBom))
    implementation(deps.okhttp)
    implementation(deps.loggingInterceptor)

    // Retrofit
    implementation(deps.retrofit)
    implementation(deps.retrofit2KotlinCoroutinesAdapter)
    implementation(deps.converterMoshi)

    // Sandwich
    implementation(deps.sandwich)
    implementation(deps.sandwichRetrofit)

    // Moshi
    ksp(deps.moshiKotlinCodegen)
    implementation(deps.moshi)
    implementation(deps.moshiKotlin)
    implementation(deps.moshiAdapters)
}
