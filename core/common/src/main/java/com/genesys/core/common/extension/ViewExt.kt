package com.genesys.core.common.extension

import android.content.Context
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2

fun View.beVisible() {
    this.visibility = View.VISIBLE
}

fun View.beVisibleIf(condition: Boolean) {
    this.visibility = if (condition) View.VISIBLE else View.GONE
}

fun View.beGone() {
    this.visibility = View.GONE
}

fun View.beInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.onDebounceClick(interval: Long = 300L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var previousClickTime: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - previousClickTime < interval) return
            else action.invoke()
            previousClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.showKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun RecyclerView.onScrollWithDebounce(
    interval: Long = 50L,
    action: (recyclerView: RecyclerView, dx: Int, dy: Int) -> Unit,
) {
    this.addOnScrollListener(object : OnScrollListener() {
        private var preScrollTime: Long = 0
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lm = recyclerView.layoutManager as? LinearLayoutManager ?: return
            if (lm.orientation == LinearLayoutManager.HORIZONTAL && dx <= 0) return
            if (lm.orientation == LinearLayoutManager.VERTICAL && dy <= 0) return

            if (SystemClock.elapsedRealtime() - preScrollTime < interval) return
            else action.invoke(recyclerView, dx, dy)
            preScrollTime = SystemClock.elapsedRealtime()
        }
    })
}

fun RecyclerView.useLoadMore(
    linearLayoutManager: LinearLayoutManager,
    adapter: Adapter<out ViewHolder>,
    offset: Int = 5,
    onLoad: () -> Unit,
) {
    this.onScrollWithDebounce { _, dx, dy ->
        if (linearLayoutManager.orientation == HORIZONTAL) {
            if ((linearLayoutManager.canLoadMore(dx, adapter, offset))) {
                onLoad.invoke()
            }
        } else {
            if ((linearLayoutManager.canLoadMore(dy, adapter, offset))) {
                onLoad.invoke()
            }
        }
    }
}

fun ViewPager2.disableParentSwipeWhenUserSwipingInside() {
    val child = getChildAt(0)
    if (child is RecyclerView) {
        child.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE ->
                    v.parent?.requestDisallowInterceptTouchEvent(true)

                MotionEvent.ACTION_UP -> {
                    v.parent?.requestDisallowInterceptTouchEvent(false)
                    v.performClick() // handle end click events
                }
            }
            false
        }
    }
}

private fun LinearLayoutManager.canLoadMore(
    scrollPos: Int,
    adapter: Adapter<out ViewHolder>,
    loadOffset: Int,
): Boolean {
    return (findLastVisibleItemPosition() >= adapter.itemCount - loadOffset) && scrollPos > 10
}

inline fun onClick(crossinline block: (View) -> Unit) = View.OnClickListener { view ->
    block.invoke(view)
}

inline fun onDebounceClick(
    interval: Long = 300L,
    crossinline action: (View) -> Unit,
) = object : View.OnClickListener {
    private var previousClickTime: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - previousClickTime < interval) return
        else action.invoke(v)
        previousClickTime = SystemClock.elapsedRealtime()
    }
}
