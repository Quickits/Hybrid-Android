package cn.quickits.hybrid.client

import android.app.Activity
import android.webkit.WebChromeClient
import android.webkit.WebView


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-12 10:49
 **/
class HybridWebChromeClient(private val activity: Activity?) : WebChromeClient() {

    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
        activity?.title = title
    }

}