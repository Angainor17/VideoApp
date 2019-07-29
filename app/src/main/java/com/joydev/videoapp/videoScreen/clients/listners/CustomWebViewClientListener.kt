package com.joydev.videoapp.videoScreen.clients.listners

/**
 * Listener for WebView loading events
 */
interface CustomWebViewClientListener {

    /*** Http or connection error*/
    fun onWebViewError()

    /*** Page was loaded successful */
    fun onPageFinished()

}