package com.joydev.videoapp.videoScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joydev.videoapp.videoScreen.models.Url


const val IFRAME_WIDTH = 560
const val IFRAME_HEIGHT = 315

private const val INIT_HTML =
    "<iframe width=\"$IFRAME_WIDTH\" height=\"$IFRAME_HEIGHT\" src=\"https:\\/\\/www.youtube.com\\/embed\\/RxfTA3XzA4Q\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen><\\/iframe>"

class VideoViewModel : ViewModel() {

    val htmlLiveData: MutableLiveData<Url> = MutableLiveData()

    fun loadInitUrl() {
        htmlLiveData.postValue(Url(INIT_HTML))
    }
}