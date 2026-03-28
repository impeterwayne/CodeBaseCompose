plugins {
    id("codebase.android.library")
    id("codebase.android.compose")
}

android {
    namespace = "com.genesys.core.designsystem"
}

dependencies {
    // Core modules
    implementation(project(":core:common"))
}
