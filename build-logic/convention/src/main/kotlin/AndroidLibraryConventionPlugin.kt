import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import libsCatalog

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(pluginId("androidLibrary"))
                apply(pluginId("kotlinAndroid"))
            }

            extensions.configure<LibraryExtension> {
                compileSdk = 36

                defaultConfig {
                    minSdk = 24
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    val consumerRulesFile = target.file("consumer-rules.pro")
                    if (consumerRulesFile.exists()) {
                        consumerProguardFiles("consumer-rules.pro")
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                    isCoreLibraryDesugaringEnabled = true
                }
            }

            dependencies {
                add("coreLibraryDesugaring", libsCatalog.findLibrary("desugarJdkLibs").get())
            }

            configureKotlinJvm()
        }
    }
}
