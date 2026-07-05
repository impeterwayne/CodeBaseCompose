
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinParcelize)
}



android {
    namespace = "com.genesys.codebase"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.genesys.codebase"
        minSdk = 24
        targetSdk = 37
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
    implementation(project(":feature:pokedex"))
    implementation(project(":feature:feature1"))
    implementation(project(":feature:feature2"))
    implementation(project(":feature:feature3"))

    // AndroidX
    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxStartupRuntime)
    implementation(libs.androidxMultidex)

    // Compose
    implementation(platform(libs.composeBom))
    implementation(libs.composeFoundation)
    implementation(libs.composeUi)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.activityCompose)
    implementation(project(":core:navigation"))
    implementation(libs.hiltNavigationCompose)
    implementation(libs.hiltLifecycleViewModelCompose)
    implementation(libs.androidxLifecycleRuntimeCompose)
    debugImplementation(libs.composeUiTooling)

    // Hilt
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)

    // ImmersionBar
    implementation(libs.immersionbar)
    implementation(libs.immersionbarComponents)
    implementation(libs.immersionbarKtx)

    // Datastore
    implementation(project(":core:datastore"))

    // Timber
    implementation(libs.timber)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxJunit)
    androidTestImplementation(libs.androidxEspressoCore)
}
