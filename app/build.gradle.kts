import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinParcelize)
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.codebase"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.genesys.codebase"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Core modules
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))

    // Feature modules
    implementation(project(":feature:template"))
    implementation(project(":feature:projects"))
    implementation(project(":feature:inbox"))
    implementation(project(":feature:settings"))

    // AndroidX
    implementation(deps.androidxCoreKtx)
    implementation(deps.androidxStartupRuntime)
    implementation(deps.androidxMultidex)

    // Compose
    implementation(platform(deps.composeBom))
    implementation(deps.composeFoundation)
    implementation(deps.composeUi)
    implementation(deps.composeUiGraphics)
    implementation(deps.composeUiToolingPreview)
    implementation(deps.activityCompose)
    implementation(deps.navigationCompose)
    implementation(deps.hiltNavigationCompose)
    implementation(deps.androidxLifecycleRuntimeCompose)
    debugImplementation(deps.composeUiTooling)

    // Hilt
    implementation(deps.hiltAndroid)
    ksp(deps.hiltCompiler)

    // ImmersionBar
    implementation(deps.immersionbar)
    implementation(deps.immersionbarKtx)

    // MMKV
    implementation(deps.mmkv)

    // Timber
    implementation(deps.timber)

    // Testing
    testImplementation(deps.junit)
    androidTestImplementation(deps.androidxJunit)
    androidTestImplementation(deps.androidxEspressoCore)
}
