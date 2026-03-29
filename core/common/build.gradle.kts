import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.library")
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.core.common"
}

dependencies {
    // AndroidX
    implementation(deps.androidxCoreKtx)
    api(deps.androidxAppcompat)
    implementation(deps.lifecycleViewmodelKtx)

    // ImmersionBar
    implementation(deps.immersionbar)
    implementation(deps.immersionbarKtx)
    implementation(deps.immersionbarComponents)

    // Coroutines
    implementation(deps.kotlinxCoroutinesCore)
    implementation(deps.kotlinxCoroutinesAndroid)

    // Gson (used by GsonExt)
    implementation(deps.gson)

    // Lifecycle runtime (used by FlowExt)
    implementation(deps.androidxLifecycleRuntimeKtx)
}
