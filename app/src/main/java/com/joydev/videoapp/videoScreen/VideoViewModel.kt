package com.joydev.videoapp.videoScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joydev.videoapp.videoScreen.clients.listners.CustomWebViewClientListener

const val IFRAME_WIDTH = 560
const val IFRAME_HEIGHT = 315

private const val INIT_HTML =
    "<iframe width=\"$IFRAME_WIDTH\" height=\"$IFRAME_HEIGHT\" src=\"https:\\/\\/www.youtube.com\\/embed\\/RxfTA3XzA4Q\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen><\\/iframe>"

class VideoViewModel : ViewModel(), CustomWebViewClientListener {

    val htmlLiveData: MutableLiveData<String> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun onWebViewError() {
        stopLoading()
    }

    override fun onPageFinished() {
        stopLoading()
    }

    fun loadInitUrl() {
        htmlLiveData.postValue(INIT_HTML)
        loadingLiveData.postValue(true)
    }

    private fun stopLoading() {
        loadingLiveData.postValue(false)
    }
}