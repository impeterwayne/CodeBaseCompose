package com.genesys.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.genesys.core.designsystem.R

@Immutable
data class AppColorScheme(
    val colorBgLayout: Color,
    val colorBgContainer: Color,
    val colorBgElevated: Color,
    val colorBgFill: Color,
    
    val colorText: Color,
    val colorTextSecondary: Color,
    
    val colorBorder: Color,
    val colorBorderSecondary: Color,
    
    val colorPrimary: Color,
    val colorTextOnPrimary: Color,

    val colorError: Color,
    val colorTextOnError: Color,

    val colorSuccess: Color,
    val colorTextOnSuccess: Color,

    val colorWarning: Color,
    val colorTextOnWarning: Color,

    val colorInfo: Color,
    val colorTextOnInfo: Color
)

@Composable
internal fun lightColorScheme() = AppColorScheme(
    colorBgLayout = colorResource(id = R.color.antd_light_bg_layout),
    colorBgContainer = colorResource(id = R.color.antd_light_bg_container),
    colorBgElevated = colorResource(id = R.color.antd_light_bg_elevated),
    colorBgFill = colorResource(id = R.color.antd_light_bg_fill),
    colorText = colorResource(id = R.color.antd_light_text),
    colorTextSecondary = colorResource(id = R.color.antd_light_text_secondary),
    colorBorder = colorResource(id = R.color.antd_light_border),
    colorBorderSecondary = colorResource(id = R.color.antd_light_border_secondary),
    colorPrimary = colorResource(id = R.color.antd_light_primary),
    colorTextOnPrimary = colorResource(id = R.color.antd_light_text_on_primary),
    colorError = colorResource(id = R.color.antd_light_error),
    colorTextOnError = colorResource(id = R.color.antd_light_text_on_error),
    colorSuccess = colorResource(id = R.color.antd_light_success),
    colorTextOnSuccess = colorResource(id = R.color.antd_light_text_on_success),
    colorWarning = colorResource(id = R.color.antd_light_warning),
    colorTextOnWarning = colorResource(id = R.color.antd_light_text_on_warning),
    colorInfo = colorResource(id = R.color.antd_light_info),
    colorTextOnInfo = colorResource(id = R.color.antd_light_text_on_info)
)

@Composable
internal fun darkColorScheme() = AppColorScheme(
    colorBgLayout = colorResource(id = R.color.antd_dark_bg_layout),
    colorBgContainer = colorResource(id = R.color.antd_dark_bg_container),
    colorBgElevated = colorResource(id = R.color.antd_dark_bg_elevated),
    colorBgFill = colorResource(id = R.color.antd_dark_bg_fill),
    colorText = colorResource(id = R.color.antd_dark_text),
    colorTextSecondary = colorResource(id = R.color.antd_dark_text_secondary),
    colorBorder = colorResource(id = R.color.antd_dark_border),
    colorBorderSecondary = colorResource(id = R.color.antd_dark_border_secondary),
    colorPrimary = colorResource(id = R.color.antd_dark_primary),
    colorTextOnPrimary = colorResource(id = R.color.antd_dark_text_on_primary),
    colorError = colorResource(id = R.color.antd_dark_error),
    colorTextOnError = colorResource(id = R.color.antd_dark_text_on_error),
    colorSuccess = colorResource(id = R.color.antd_dark_success),
    colorTextOnSuccess = colorResource(id = R.color.antd_dark_text_on_success),
    colorWarning = colorResource(id = R.color.antd_dark_warning),
    colorTextOnWarning = colorResource(id = R.color.antd_dark_text_on_warning),
    colorInfo = colorResource(id = R.color.antd_dark_info),
    colorTextOnInfo = colorResource(id = R.color.antd_dark_text_on_info)
)

internal val LocalAppColorScheme = staticCompositionLocalOf<AppColorScheme> {
    error("No AppColorScheme provided")
}
