
plugins {
    id("codebase.android.feature")
    id("kotlin-parcelize")
}


android {
    namespace = "com.genesys.feature.template"
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(libs.orbitViewmodel)
    implementation(libs.orbitCompose)
}
