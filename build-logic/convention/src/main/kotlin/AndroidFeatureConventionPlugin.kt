import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("codebase.android.library")
                apply("codebase.android.hilt")
                apply("codebase.android.compose")
            }

            dependencies {
                add("implementation", project(":core:model"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:common"))
                add("implementation", project(":core:designsystem"))

                add("implementation", library("hiltNavigationCompose"))
                add("implementation", library("lifecycleViewmodelKtx"))
                add("implementation", library("kotlinxCoroutinesAndroid"))
            }
        }
    }
}
