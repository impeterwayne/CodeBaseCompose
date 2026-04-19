import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("codebase.android.feature")
    id("kotlin-parcelize")
}

val deps = the<LibrariesForLibs>()

android {
    namespace = "com.genesys.feature.template"
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(deps.orbitViewmodel)
    implementation(deps.orbitCompose)
}
