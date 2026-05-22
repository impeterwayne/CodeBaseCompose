
plugins {
    id("codebase.android.feature")
}


android {
    namespace = "com.genesys.feature.feature2"
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(libs.orbitViewmodel)
    implementation(libs.orbitCompose)
}
