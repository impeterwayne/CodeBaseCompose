# 🛠️ Development & Setup Guide

This document provides step-by-step instructions for configuring, building, testing, and developing **SkillHub**.

---

## 🔌 1. Gradle MCP Server Integration (Recommended for AI Agents)

When executing development, inspection, or build operations within an agentic environment, **always prefer using the Gradle MCP Server** instead of running raw terminal commands. The Gradle MCP server provides safe, sandboxed, and structured APIs to interact with the project build system.

### Available MCP Tools

| MCP Tool | Description & Usage |
| --- | --- |
| `gradle` | Execute Gradle tasks directly (e.g. `clean`, `test`, `assembleDebug`). |
| `inspect_build` | Get detailed information about the Gradle build, including project structures and tasks. |
| `inspect_dependencies` | Analyze dependencies configured inside the Gradle project. |
| `gradle_docs` | Read the Gradle documentation and guides. |
| `lookup_maven_versions` | Search and check available versions of maven libraries. |
| `read_dependency_sources` | Retrieve source code references for external library dependencies. |
| `search_dependency_sources` | Search within the sources of dependencies. |

### How to use the MCP Server (Agent Instructions)
If the Gradle MCP server is available, invoke its tools with structured arguments. For example, to run unit tests:
- **Server Name:** `gradle`
- **Tool Name:** `gradle`
- **Arguments:** `{ "tasks": [":core:common:test"] }`

Using this tool isolates execution logs and permits the MCP client to handle environmental configurations gracefully.

---

## 🚀 2. Gradle Command Reference (Fallback / Local Terminal)

All build operations use the standard Gradle Wrapper script located in the project root directory. Use these commands when developing locally outside the MCP environment.

### Windows (PowerShell)
Use the `.\gradlew.bat` script:
```powershell
# Clean all build directories
.\gradlew.bat clean

# Compile and package debug APK
.\gradlew.bat assembleDebug

# Run all local JVM unit tests
.\gradlew.bat test

# Run local JVM tests on a specific module
.\gradlew.bat :core:common:test
```

### macOS & Linux
Use `./gradlew` script (ensure execution permissions are set via `chmod +x gradlew`):
```bash
# Clean all build directories
./gradlew clean

# Compile and package debug APK
./gradlew assembleDebug

# Run all local JVM unit tests
./gradlew test
```

---

## 🧪 3. Testing Architecture

SkillHub encourages thorough test-driven development:
* **Local Unit Tests (`test`):** Placed in the `src/test/java` directories of modules. Used to verify use cases in `:core:domain`, logic in `:core:data`, and MVI container actions inside features using Orbit MVI's built-in testing helpers (`orbit-test` library).
* **Android Instrumentation Tests (`androidTest`):** Placed in `src/androidTest/java` within `:app` or `:feature:*`. Used for UI test assertions via Compose Test Rules and Espresso.
