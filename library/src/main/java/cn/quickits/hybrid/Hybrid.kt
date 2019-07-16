package cn.quickits.hybrid

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.GenericLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import cn.quickits.hybrid.api.AbsApi
import cn.quickits.hybrid.api.EnvApi
import cn.quickits.hybrid.client.HybridWebChromeClient
import cn.quickits.hybrid.client.HybridWebViewClient
import cn.quickits.hybrid.util.Logger


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-12 10:55
 **/
@SuppressLint("RestrictedApi")
class Hybrid(private val activity: AppCompatActivity, private val webView: WebView) : GenericLifecycleObserver {

    private val apiManager = ApiManager()

    init {
        activity.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner?, event: Lifecycle.Event?) {
        Logger.d(event?.name)

        when (event) {
            Lifecycle.Event.ON_CREATE -> {

            }

            Lifecycle.Event.ON_DESTROY -> {
                webView.setOnKeyListener(null)
                webView.webViewClient = null
                webView.webChromeClient = null
                webView.removeAllViews()
                webView.destroy()
            }

            else -> {

            }
        }
    }

    /**
     *
     */
    fun handleUrl(url: Uri): Boolean = apiManager.handleUrl(url, webView)

    /**
     *
     */
    fun registerApi(api: AbsApi) = apiManager.registerApi(api)

    /**
     *
     */
    fun setupWebView() {
        // 注册 API
        registerApi(EnvApi())
        HybridConfig.customApi.forEach { registerApi(it) }

        // 配置 WebView
        setupWebClient(webView)
        setupWebSettings(webView.settings)
    }

    /**
     * 设置 WebClient
     */
    private fun setupWebClient(webView: WebView) {
        webView.webViewClient = HybridWebViewClient(hybrid = this)
        webView.webChromeClient = HybridWebChromeClient(activity = activity)
    }

    /**
     * 设置 WebSettings
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebSettings(settings: WebSettings) {
        settings.setSupportZoom(true)
        settings.setAppCacheEnabled(true)
        settings.loadWithOverviewMode = true
        settings.allowFileAccess = true
        settings.javaScriptEnabled = true
        settings.allowFileAccess = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.useWideViewPort = true
        settings.textZoom = 100
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN

        settings.setGeolocationEnabled(true)
        val dir = activity.getDir("geolocation", Context.MODE_PRIVATE).path
        settings.setGeolocationDatabasePath(dir)
    }

}