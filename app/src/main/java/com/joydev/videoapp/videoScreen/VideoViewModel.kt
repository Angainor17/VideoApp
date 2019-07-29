package com.joydev.videoapp.videoScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joydev.videoapp.videoScreen.models.Url

private const val INIT_HTML = "<iframe width=\"560\" height=\"315\" src=\"https:\\/\\/www.youtube.com\\/embed\\/RxfTA3XzA4Q\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen><\\/iframe>"

class VideoViewModel : ViewModel() {

    val webViewLiveData: MutableLiveData<Url> = MutableLiveData()

    fun loadInitUrl() {
        webViewLiveData.postValue(Url(INIT_HTML))
    }
}