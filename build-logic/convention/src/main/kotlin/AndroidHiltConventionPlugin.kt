import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.dagger.hilt.android")
            pluginManager.apply("com.google.devtools.ksp")

            dependencies {
                add("implementation", "com.google.dagger:hilt-android:2.53")
                add("ksp", "com.google.dagger:hilt-compiler:2.53")
            }

            // When KAPT is also present (e.g. for Epoxy), the Hilt Gradle plugin
            // auto-adds hilt-compiler to KAPT, causing duplicates with KSP output.
            // Remove hilt-compiler from KAPT after all plugins are applied.
            pluginManager.withPlugin("kotlin-kapt") {
                afterEvaluate {
                    configurations.filter { it.name.startsWith("kapt") }.forEach { config ->
                        config.dependencies.removeAll { dep ->
                            dep.group == "com.google.dagger" && dep.name == "hilt-compiler"
                        }
                    }
                }
            }
        }
    }
}
