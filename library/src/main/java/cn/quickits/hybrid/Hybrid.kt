package cn.quickits.hybrid

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.webkit.WebSettings
import android.webkit.WebView
import cn.quickits.hybrid.client.HybridWebChromeClient
import cn.quickits.hybrid.client.HybridWebViewClient


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-12 10:55
 **/
class Hybrid(private val activity: Activity, private val webView: WebView) {

    /**
     * 
     */
    fun setupWebView() {
        setupWebClient(webView)
        setupWebSettings(webView.settings)
    }

    /**
     *
     */
    private fun setupWebClient(webView: WebView) {
        webView.webViewClient = HybridWebViewClient()
        webView.webChromeClient = HybridWebChromeClient()
    }

    /**
     *
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