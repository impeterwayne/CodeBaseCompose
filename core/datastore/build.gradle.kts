plugins {
    id("codebase.android.library")
}

android {
    namespace = "com.genesys.core.datastore"
}

dependencies {
    implementation(libs.mmkv)
}
