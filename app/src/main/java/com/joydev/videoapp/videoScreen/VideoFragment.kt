package com.joydev.videoapp.videoScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.joydev.videoapp.R
import com.joydev.videoapp.utils.getScreenWidth
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : Fragment() {

    private lateinit var viewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        webView.setInitialScale(getInitialScale())
        webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            pluginState = WebSettings.PluginState.ON
        }

        webView.webChromeClient = object : WebChromeClient() {}
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }

        viewModel.loadInitUrl()
    }

    /** Initial scale based on video width */
    private fun getInitialScale() = ((getScreenWidth(activity!!) / IFRAME_WIDTH.toFloat()) * 97.5).toInt()

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)

        viewModel.htmlLiveData.observe(this) {
            webView.loadData(it.value, "text/html", "UTF-8")
        }
    }
}