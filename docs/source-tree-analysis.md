# Source Tree Analysis & Folder Structures

This document provides a highly detailed, annotated directory mapping of the **SkillHub** project root (`D:\Quest\CodebaseCompose`). The codebase uses a modern, modularized Gradle structure separating core libraries, app entry, and screen features.

---

## 📂 Multi-Module Directory Tree

```
D:\Quest\CodebaseCompose\
├── build-logic/                # Gradle convention plugins sharing build configurations
├── gradle/                     # Gradle wrapper assets and Version Catalog definitions
│   └── libs.versions.toml      # Modern centralized version catalog of dependencies
├── app/                        # Main Android Application module (App entry point)
│   ├── src/main/java/com/genesys/codebase/
│   │   ├── di/                 # Root-level Dependency Injection bindings (Hilt)
│   │   ├── navigation/         # NavHost, AppState, and Bottom Navigation routing
│   │   └── MainApplication.kt  # Android Application entry point (initializes MMKV)
│   └── build.gradle.kts        # App build configurations and core/feature dependency imports
├── feature/                    # UI Screen feature modules implementing Orbit MVI
│   ├── template/               # [Gold Standard Reference] Core template feature
│   │   └── src/main/java/com/genesys/feature/template/
│   │       ├── main/           # MVI classes (Contract, ViewModel, Composable Screens)
│   │       │   ├── components/ # Sub-components (TemplateHero, TemplateCollectionsList, etc.)
│   │       │   ├── MainContract.kt      # State, Actions, and Side Effects contracts
│   │       │   ├── MainViewModel.kt     # Hilt ViewModel performing business logic
│   │       │   ├── TemplateScreen.kt    # Stateless hoister for templates overview
│   │       │   └── TemplateDetailScreen.kt # Details view Composable
│   │       └── navigation/
│   │           └── TemplateGraph.kt     # Entry provider wrapping Jetpack Navigation 3
│   ├── projects/               # Projects-related feature module
│   ├── inbox/                  # Inbox and messages feature module
│   └── settings/               # App configuration and profile feature module
└── core/                       # Shared utility and architectural layers (Domain, Data, UI Design)
    ├── common/                 # Base classes, including BaseViewModel.kt and MVI traits
    ├── model/                  # Pure Kotlin data models (independent of Android or database)
    ├── network/                # Retrofit, DTO models, and ApiService implementation
    ├── database/               # Room DB schema, Entity schemas, Dao interfaces, and Converters
    ├── datastore/              # Tencent MMKV high-performance persistent key-value helper
    ├── domain/                 # Business rule boundaries and UseCase implementations
    ├── navigation/             # Navigation bridges (AppNavigator) and type-safe key definitions (Route)
    └── designsystem/           # Premium Custom Composable UI kit (AppButton, AppPanel, AppTheme)
```

---

## 🔍 Module Roles & Integration Points

### 1. The App Module (`:app`)
* **Role:** Application assembler. It binds together feature modules, coordinates dependencies via Hilt, and acts as the entry host for navigation.
* **Entry Point:** [MainApplication.kt](file:///D:/Quest/CodebaseCompose/app/src/main/java/com/genesys/codebase/MainApplication.kt) initializes Hilt and Tencent MMKV.
* **Navigation Entry:** [NavHost.kt](file:///D:/Quest/CodebaseCompose/app/src/main/java/com/genesys/codebase/navigation/NavHost.kt) creates the root `NavHost` and binds the top-level bottom navigation stacks.

### 2. Feature Modules (`:feature:*`)
* **Role:** Screen orchestration layer. Each module represents a high-level user flow.
* **Reference Implementation:** **`:feature:template`**
  * All feature additions should mimic the directory layout of this module. It provides a complete sandbox from contract boundary definition up to custom UI component segregation.

### 3. Core Modules (`:core:*`)
* **`:core:designsystem`**: Houses custom styling design guidelines (`AppTheme`, `AppText`, `AppPanel`, `AppButton`). Avoid direct imports of vanilla material widgets unless extending core custom design features.
* **`:core:navigation`**: Binds the type-safe routing API. Defining a new route object `Route` here enables any feature module to navigate directly to it without compile-time knowledge of other feature graphs.
* **`:core:network`** & **`:core:database`**: Provide the primary remote and local caching pipelines for data orchestration.
