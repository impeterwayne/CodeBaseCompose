
plugins {
    id("codebase.android.feature")
    id("kotlin-parcelize")
}


android {
    namespace = "com.genesys.feature.pokedex"
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(libs.orbitViewmodel)
    implementation(libs.orbitCompose)
    implementation(libs.landscapist.glide)
    implementation(libs.landscapist.placeholder)
    implementation(libs.landscapist.animation)
}
