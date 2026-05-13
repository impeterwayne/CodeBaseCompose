import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.feature")
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.feature.inbox"
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(deps.orbitViewmodel)
    implementation(deps.orbitCompose)
}
