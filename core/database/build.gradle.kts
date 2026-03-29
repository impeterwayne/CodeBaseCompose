import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.library")
    id("codebase.android.hilt")
    alias(libs.plugins.kotlinParcelize)
}

val deps = the<LibrariesForLibs>()

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
    implementation(deps.androidxRoomRuntime)
    ksp(deps.androidxRoomCompiler)
    implementation(deps.androidxRoomKtx)

    // Moshi (for type converters)
    implementation(deps.moshi)
    implementation(deps.moshiKotlin)

    // Coroutines
    implementation(deps.kotlinxCoroutinesCore)
    implementation(deps.kotlinxCoroutinesAndroid)
}
