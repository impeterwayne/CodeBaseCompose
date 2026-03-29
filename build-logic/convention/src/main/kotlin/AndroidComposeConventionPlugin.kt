import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(pluginId("kotlinCompose"))

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    compose = true
                }
            }

            dependencies {
                val bom = platform(library("composeBom"))
                add("implementation", bom)
                add("implementation", library("composeUi"))
                add("implementation", library("composeUiGraphics"))
                add("implementation", library("composeUiToolingPreview"))
                add("implementation", library("composeFoundation"))
                add("implementation", library("composeRuntime"))
                add("implementation", library("androidxLifecycleRuntimeCompose"))
                add("debugImplementation", library("composeUiTooling"))
            }
        }
    }
}
