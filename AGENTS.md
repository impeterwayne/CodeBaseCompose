# Repository Guidelines

## Project Structure & Module Organization
- `app/` — Android application (Kotlin). Presentation in `presenter/`, business logic in `domain/`, data in `data/`, DI modules in `di/`, utilities in `utils/`, bindings in `binding/`. Resources live in `app/src/main/res`.
- `xxpermission/` — embedded permissions library (Java). Treat as third‑party; avoid edits unless scoped fixes.
- Root Gradle files: `settings.gradle`, `build.gradle`, `gradle.properties`. Use the Gradle wrapper (`gradlew`, `gradlew.bat`).
- Tests: unit tests in `app/src/test/java/...`; instrumentation tests in `app/src/androidTest/java/...`.

## Build, Test, and Development Commands
- Build debug APK: `./gradlew :app:assembleDebug` (Windows: `./gradlew.bat ...`).
- Install on device: `./gradlew :app:installDebug`.
- Unit tests: `./gradlew :app:testDebugUnitTest`.
- Instrumentation tests: `./gradlew :app:connectedDebugAndroidTest` (device/emulator required).
- Lint (Android Lint): `./gradlew :app:lint` → report at `app/build/reports/lint-results.html`.
- Clean: `./gradlew clean`.

## Coding Style & Naming Conventions
- Kotlin-first; 4-space indentation; keep lines reasonable (~120 chars).
- Classes/files: UpperCamelCase. Functions/vars: lowerCamelCase. Constants: UPPER_SNAKE_CASE. Packages: lowercase.
- Android resources: snake_case with type prefixes (e.g., `activity_main.xml`, `ic_share.xml`, `color_primary`).
- Architecture: keep IO/data in `data/`, mapping and models in `domain/`, UI in `presenter/`. Avoid business logic in views.
- DI: use Hilt modules under `di/` (`@Module`, `@InstallIn`). Configure endpoints via `BuildConfig` (see `app/build.gradle`).

## Architecture & MVVM (Read First)
- Follow `Architecture.md` for the layered MVVM structure and responsibilities.
- All feature ViewModels must extend `BaseViewModel` (see `app/src/main/java/com/genesys/v1/codebase/presenter/base/common/BaseViewModel.kt`).
- Views (Activities/Fragments/adapters) live in `presenter/` and interact only with ViewModel state/events.
- Domain remains UI-agnostic; repositories and models under `domain/` define contracts consumed by `data/`.
- Data implements domain contracts; perform DTO-to-domain mapping within `data/`.

See: `Architecture.md`.

## UI Layout Guidelines
- Use `Layout.md` when creating or modifying XML layouts.
- Prefer flat hierarchies; choose `FrameLayout`/`LinearLayout` per decision tree before `ConstraintLayout`.
- Avoid overdraw: set app-wide background via theme, not root containers.
- Externalize dimensions, colors, and strings to resources.

See: `Layout.md`.

## RecyclerView & Epoxy
- Use the patterns in `RecyclerView.md` for lists and carousels.
- Create item XML, then an Epoxy model using the provided base holder in `presenter/base/epoxy/`.
- In Fragments, build models via the generated DSL inside `withModels { ... }`; always set stable `id`s.
- Flow data to the UI via `StateFlow` from a `BaseViewModel`; observe in views and rebuild models as needed.

See: `RecyclerView.md`.

## Testing Guidelines
- Frameworks: JUnit (unit), Espresso (androidTest). Prefer testing use cases/repos over UI; mock network/DB boundaries.
- Location: mirror source package; name tests with `*Test` (e.g., `TemplateCollectionsDaoTest`).
- Run locally before PR: `./gradlew :app:testDebugUnitTest :app:connectedDebugAndroidTest` (as applicable).

## Commit & Pull Request Guidelines
- Commits: short, imperative subject (≤50 chars). Examples: "add data and api template", "fix presenter caching". Add a body for rationale and reference issues (e.g., `#123`) when helpful.
- PRs: include description, linked issues, screenshots/GIFs for UI, test plan, and notes on schema/API or permission changes. Ensure `clean`, `lint`, and tests pass.

## Security & Configuration Tips
- Do not commit secrets. Keep keys in `local.properties`/Gradle properties; access via `BuildConfig`.
- Use `xxpermission` for runtime permissions; avoid duplicating permission logic.
