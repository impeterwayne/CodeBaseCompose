import org.gradle.api.InvalidUserDataException
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libsCatalog
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.library(alias: String): String {
    val dependency = libsCatalog.findLibrary(alias).orElseThrow {
        InvalidUserDataException("Library alias '$alias' was not found in the libs catalog.")
    }.get()
    val version = dependency.versionConstraint.requiredVersion

    return buildString {
        append(dependency.module.group)
        append(':')
        append(dependency.module.name)
        if (version.isNotBlank()) {
            append(':')
            append(version)
        }
    }
}

fun Project.pluginId(alias: String): String =
    libsCatalog.findPlugin(alias).orElseThrow {
        InvalidUserDataException("Plugin alias '$alias' was not found in the libs catalog.")
    }.get().pluginId
