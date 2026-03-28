import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("codebase.android.library")
                apply("codebase.android.hilt")
            }

            dependencies {
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:model"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:common"))

                add("implementation", "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
                add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
            }
        }
    }
}
