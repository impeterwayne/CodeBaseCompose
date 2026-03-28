package com.genesys.core.common.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import androidx.core.net.toUri

fun ImageView.loadImageWithGlide(thumb: Any?, @DrawableRes error: Int? = null) {
    Glide.with(this)
        .load(thumb)
        .error(error)
        .into(this)
}

fun ImageView.loadGlide(
    thumb: Any?,
    onReady: ((Any?) -> Unit)? = null,
    onFailed: (() -> Unit)? = null
) {
    Glide.with(this).load(thumb).listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>,
            isFirstResource: Boolean
        ): Boolean {
            onFailed?.invoke()
            return false
        }

        override fun onResourceReady(
            resource: Drawable,
            model: Any,
            target: Target<Drawable>?,
            dataSource: DataSource,
            isFirstResource: Boolean
        ): Boolean {
            onReady?.invoke(resource)
            return false
        }
    }).into(this)
}

fun ImageView.loadImageGlideWithDp(url: String, dp: Int) {
    val radiusPx = (dp * context.resources.displayMetrics.density).toInt()
    Glide.with(this)
        .load(url.toUri())
        .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(radiusPx)))
        .into(this)
}

