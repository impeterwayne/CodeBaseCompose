plugins {
    id("codebase.android.feature")
}

android {
    namespace = "com.genesys.feature.settings"
}

dependencies {
    implementation(project(":core:navigation"))
}
