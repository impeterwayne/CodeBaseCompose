plugins {
    id("codebase.android.feature")
}

android {
    namespace = "com.genesys.feature.feature1"
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(libs.orbitViewmodel)
    implementation(libs.orbitCompose)
}
