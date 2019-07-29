package com.joydev.videoapp.videoScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.joydev.videoapp.R
import com.joydev.videoapp.utils.getScreenWidth
import com.joydev.videoapp.videoScreen.clients.ChromeWebViewClient
import com.joydev.videoapp.videoScreen.clients.CustomWebViewClient
import kotlinx.android.synthetic.main.fragment_video.*

private const val VIDEO_WIDTH_PERCENT = 97.5f
private const val DATA_MIME_TYPE = "text/html"
private const val DATA_ENCODING = "UTF-8"

class VideoFragment : Fragment() {

    private lateinit var viewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        if (savedInstanceState == null) {
            viewModel.loadInitUrl()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        swipeRefresh.setOnRefreshListener {
            viewModel.loadInitUrl()
        }

        webView.setInitialScale(getInitialScale())
        webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            allowFileAccess = true
            setAppCacheEnabled(true)
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            pluginState = WebSettings.PluginState.ON
        }

        webView.webChromeClient = ChromeWebViewClient(activity!!)
        webView.webViewClient = CustomWebViewClient(viewModel)
    }

    /** Initial scale based on video width */
    private fun getInitialScale() =
        ((getScreenWidth(activity!!) / IFRAME_WIDTH.toFloat()) * VIDEO_WIDTH_PERCENT).toInt()

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)

        viewModel.htmlLiveData.observe(this) {
            webView.loadData(it, DATA_MIME_TYPE, DATA_ENCODING)
        }

        viewModel.loadingLiveData.observe(this) {
            swipeRefresh.isRefreshing = it
        }
    }
}