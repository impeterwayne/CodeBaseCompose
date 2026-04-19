package com.genesys.core.navigation

import androidx.navigation3.ui.NavDisplayKt
import androidx.navigation3.runtime.NavBackStack
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.valueParameters

fun printNavDisplayParams() {
    println("NavDisplay parameters:")
    val cls = Class.forName("androidx.navigation3.ui.NavDisplayKt")
    cls.declaredMethods.forEach { method -> 
        if (method.name.contains("NavDisplay")) {
            println("Method: ${method.name}")
            method.parameters.forEach { param ->
                println(" - param: ${param.name} : ${param.type.simpleName}")
            }
        }
    }
}
