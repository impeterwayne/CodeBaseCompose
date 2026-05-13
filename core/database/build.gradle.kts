
plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
    alias(libs.plugins.kotlinParcelize)
}


android {
    namespace = "com.genesys.core.database"

    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    implementation(project(":core:model"))

    // Room
    implementation(libs.androidxRoomRuntime)
    ksp(libs.androidxRoomCompiler)
    implementation(libs.androidxRoomKtx)

    // Gson (for type converters)
    implementation(libs.gson)

    // Coroutines
    implementation(libs.kotlinxCoroutinesCore)
    implementation(libs.kotlinxCoroutinesAndroid)
}
