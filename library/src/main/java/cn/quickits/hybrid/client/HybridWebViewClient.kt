package cn.quickits.hybrid.client

import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.*
import cn.quickits.hybrid.util.Logger


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-12 10:48
 **/
class HybridWebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        Logger.d("shouldOverrideUrlLoading: $url")
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        Logger.d("shouldOverrideUrlLoading: ${request?.url}")
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        Logger.d("onPageStarted: $url")
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        Logger.d("onPageFinished: $url")
        super.onPageFinished(view, url)
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        Logger.d("onReceivedError: ${request?.url} $error")
        super.onReceivedError(view, request, error)
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        Logger.d("onReceivedSslError: $error")
        super.onReceivedSslError(view, handler, error)
    }

}