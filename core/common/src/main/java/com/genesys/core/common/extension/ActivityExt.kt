package com.genesys.core.common.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

inline fun <reified A : Activity> Context.startActivity(
    configExtras: Intent.() -> Unit = {},
) {
    ContextCompat.startActivity(
        this@startActivity,
        Intent(this@startActivity, A::class.java).apply(configExtras),
        null
    )
}

fun FragmentActivity.registerStartActivityForResult(
    onResultCallback: (ActivityResult) -> Unit,
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        onResultCallback.invoke(it)
    }
}

fun Fragment.registerStartActivityForResult(
    onResultCallback: (ActivityResult) -> Unit,
): ActivityResultLauncher<Intent> {
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        onResultCallback.invoke(it)
    }
}

inline fun ActivityResult.doOnResultOK(block: (ActivityResult) -> Unit) {
    if (resultCode == Activity.RESULT_OK) {
        block.invoke(this)
    }
}

fun FragmentActivity.addFragment(
    @IdRes containerId: Int, fragment: Fragment,
    t: (transaction: FragmentTransaction) -> Unit = {}, backStackString: String? = null,
) {
    if (supportFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) == null) {
        val transaction = supportFragmentManager.beginTransaction()
        t.invoke(transaction)
        transaction.add(containerId, fragment, fragment.javaClass.simpleName)
        if (backStackString != null) {
            transaction.addToBackStack(backStackString)
        }
        transaction.commitAllowingStateLoss()
        supportFragmentManager.executePendingTransactions()
    }
}

fun FragmentActivity.registerRequestMultiplePermissionsResult(
    onResultCallback: (Map<String, Boolean>) -> Unit,
): ActivityResultLauncher<Array<String>> {
    return registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        onResultCallback.invoke(it)
    }
}

fun Fragment.registerRequestMultiplePermissionsResult(
    onResultCallback: (Map<String, Boolean>) -> Unit,
): ActivityResultLauncher<Array<String>> {
    return registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        onResultCallback.invoke(it)
    }
}

fun FragmentActivity.registerRequestPermissionsResult(
    onResultCallback: (Boolean) -> Unit,
): ActivityResultLauncher<String> {
    return registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        onResultCallback.invoke(it)
    }
}
