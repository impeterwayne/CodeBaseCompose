---
name: android-runtime-permissions
description: Skill for requesting Android runtime permissions using the Kotlin DSL from impeterwayne/XXPermission-ktx and integrating it within Jetpack Compose features.
---

# Android Runtime Permissions DSL (XXPermission-ktx) Skill

## Overview

This skill provides instructions for requesting Android runtime permissions using the Kotlin DSL wrapper [impeterwayne/XXPermission-ktx](https://github.com/impeterwayne/XXPermission-ktx), which wraps the core `getActivity/XXPermissions` library.

Use this skill when:
- Implementing runtime permission request flows (e.g., Notification, Camera, Storage, Location) inside activities, fragments, or Jetpack Compose screens.
- Designing rationales or custom dialogs to explain why specific permissions are required.
- Directing users to app settings when permissions are permanently denied.

---

## 1. Setup & Dependencies

Before requesting runtime permissions, verify that the module's `build.gradle.kts` declares the dependency:

```kotlin
// In the module's build.gradle.kts
dependencies {
    implementation(libs.xxpermissionKtx)
}
```

Ensure the library is defined in the root `gradle/libs.versions.toml`:
```toml
[versions]
xxpermissionKtx = "1.0.2"

[libraries]
xxpermissionKtx = { module = "io.github.impeterwayne:xxpermission-ktx", version.ref = "xxpermissionKtx" }
```

---

## 2. Best Practices & Codebase Rules

| Category | Do | Don't |
| :--- | :--- | :--- |
| **No Hardcoded Strings** | **MANDATORY**: Extract all text, descriptions, and rationales for dialogs into `strings.xml`. Never hardcode raw strings. | Hardcode visual dialog text or explanation messages directly in Kotlin files. |
| **Safe Activity Casting** | Always check and cast Compose context to `Activity` safely using `val activity = context as? Activity` and handle nullability (e.g., inside previews). | Force cast context using `context as Activity` which will crash previews and non-activity hosts. |
| **Settings Redirection** | Use the library's `userResult.onResult(true)` mechanism in `onDoNotAskAgain` to redirect users to settings. | Manually construct settings Intents for system navigation. |
| **All-Granted Checks** | Check `allGranted: Boolean` flag in `onResult` before executing state-changing logic or launching background workers. | Assume permissions are granted if the callback is fired. |

---

## 3. Pre-Delivery Checklist

Ensure your implementation of permission request flow complies with all of the following rules:

- [ ] **No Hardcoded Strings**: All dialogue titles, descriptions, and buttons are loaded from `strings.xml` using `context.getString(...)` or Compose `stringResource(...)`.
- [ ] **Preview Compatibility**: The Jetpack Compose preview handles cases where the activity is null or mocked.
- [ ] **Explicit Result Handling**: Proper action or navigation is triggered in `onResult` regardless of whether the user accepted or denied.
- [ ] **Manifest Declaration**: The requested permission is declared in `AndroidManifest.xml`.

---

## 4. Custom Rationale & Settings Dialogs in Compose (Wrapper Pattern)

When requesting permissions in Jetpack Compose, the best practice is to encapsulate the request states, DSL callbacks, and the custom dialog rendering inside a Composable wrapper function. Furthermore, use a centralized mapper to automatically resolve string resources and illustrations based on the requested permissions. This keeps UI views completely clean and stateless.

The global wrapper helper is located in `:core:designsystem` under `global.infinitytech.linguai.app.core.designsystem.component.PermissionRequester`.

### Implementation Reference

```kotlin
data class PermissionDialogResources(
    val titleResId: Int,
    val descriptionResId: Int,
    @DrawableRes val illustrationResId: Int
)

fun mapPermissionsToResources(permissions: List<IPermission>): PermissionDialogResources {
    return if (permissions.contains(PermissionLists.getCameraPermission())) {
        PermissionDialogResources(
            titleResId = R.string.designsystem_permission_allow_access,
            descriptionResId = R.string.designsystem_permission_camera_rationale,
            illustrationResId = R.drawable.img_permission_camera
        )
    } else if (permissions.contains(PermissionLists.getRecordAudioPermission())) {
        PermissionDialogResources(
            titleResId = R.string.designsystem_permission_allow_access,
            descriptionResId = R.string.designsystem_permission_record_rationale,
            illustrationResId = R.drawable.img_permission_mic
        )
    } else {
        PermissionDialogResources(
            titleResId = R.string.designsystem_permission_allow_access,
            descriptionResId = R.string.designsystem_permission_notification_rationale,
            illustrationResId = R.drawable.ic_reminder
        )
    }
}
```

```kotlin
@Composable
fun rememberPermissionRequester(
    permissions: List<IPermission>,
    onResult: (Boolean) -> Unit
): () -> Unit {
    val context = LocalContext.current
    val activity = context as? Activity

    var pendingCallback by remember { mutableStateOf<(() -> Unit)?>(null) }
    var onDismiss by remember { mutableStateOf<(() -> Unit)?>(null) }
    var dialogType by remember { mutableStateOf<PermissionDialogType?>(null) }

    if (dialogType != null) {
        val resources = mapPermissionsToResources(permissions)
        val buttonText = when (dialogType) {
            PermissionDialogType.RATIONALE -> stringResource(id = R.string.designsystem_permission_allow_access)
            PermissionDialogType.SETTINGS -> stringResource(id = R.string.designsystem_permission_go_to_settings)
            else -> ""
        }

        AppPermissionDialog(
            illustrationRes = resources.illustrationResId,
            title = stringResource(id = resources.titleResId),
            description = stringResource(id = resources.descriptionResId),
            primaryButtonText = buttonText,
            onPrimaryButtonClick = {
                pendingCallback?.invoke()
                dialogType = null
                pendingCallback = null
                onDismiss = null
            },
            onDismissRequest = {
                onDismiss?.invoke()
                dialogType = null
                pendingCallback = null
                onDismiss = null
            }
        )
    }

    return {
        if (activity != null) {
            activity.xxPermissions {
                permissions(permissions)
                onDoNotAskAgain { _, userResult ->
                    pendingCallback = { userResult.onResult(true) }
                    onDismiss = { userResult.onResult(false) }
                    dialogType = PermissionDialogType.SETTINGS
                }
                onShouldShowRationale { _, onUserResult ->
                    pendingCallback = { onUserResult.onResult(true) }
                    onDismiss = { onUserResult.onResult(false) }
                    dialogType = PermissionDialogType.RATIONALE
                }
                onResult { allGranted, _, _ ->
                    onResult(allGranted)
                }
            }
        } else {
            onResult(false)
        }
    }
}
```

### Usage in Composable Views

Import the helper from the core design system:
```kotlin
import global.infinitytech.linguai.app.core.designsystem.component.rememberPermissionRequester
```

Initialize it inside the Composable function and call the returned trigger lambda:

```kotlin
val requestCameraPermission = rememberPermissionRequester(
    permission = PermissionLists.getCameraPermission(),
    onResult = { allGranted ->
        if (allGranted) {
            onCameraToggled(true)
        }
    }
)

// Trigger on click:
Box(modifier = Modifier.clickable { requestCameraPermission() }) { ... }
```
