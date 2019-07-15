package cn.quickits.hybrid.client

import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.webkit.*
import cn.quickits.hybrid.Hybrid
import cn.quickits.hybrid.util.Logger


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-12 10:48
 **/
class HybridWebViewClient(val hybrid: Hybrid) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url: Uri = request?.url ?: return super.shouldOverrideUrlLoading(view, request)

        return hybrid.handleUrl(url) || super.shouldOverrideUrlLoading(view, request)
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