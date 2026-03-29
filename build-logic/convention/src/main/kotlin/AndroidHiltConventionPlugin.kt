import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(pluginId("hiltAndroid"))
            pluginManager.apply(pluginId("ksp"))

            dependencies {
                add("implementation", library("hiltAndroid"))
                add("ksp", library("hiltCompiler"))
            }
        }
    }
}
