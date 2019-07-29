package com.joydev.videoapp.videoScreen.clients

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.widget.FrameLayout

private const val FULLSCREEN_SYSTEM_VISIBILITY_FLAG = 3846

/*** Custom Chrome webView Client for activity FullScreen Mode*/
class ChromeWebViewClient(private val activity: Activity) : WebChromeClient() {

    private var customView: View? = null
    private var originalOrientation: Int = 0
    private var originalSystemUiVisibility: Int = 0

    override fun onHideCustomView() {
        (getDecorView() as FrameLayout).removeView(this.customView)
        customView = null
        getDecorView().systemUiVisibility = this.originalSystemUiVisibility
        activity.requestedOrientation = this.originalOrientation
    }

    private fun getDecorView() = activity.window.decorView

    override fun onShowCustomView(paramView: View, paramCustomViewCallback: CustomViewCallback) {
        if (customView != null) {
            onHideCustomView()
            return
        }
        customView = paramView
        originalSystemUiVisibility = activity.window.decorView.systemUiVisibility
        originalOrientation = activity.requestedOrientation

        (getDecorView() as FrameLayout).addView(
            this.customView, FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        getDecorView().systemUiVisibility = FULLSCREEN_SYSTEM_VISIBILITY_FLAG
    }
}