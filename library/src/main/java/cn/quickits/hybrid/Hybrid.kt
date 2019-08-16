package cn.quickits.hybrid

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
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
class Hybrid private constructor() : GenericLifecycleObserver {

    private var activity: FragmentActivity? = null

    private var fragment: Fragment? = null

    private var webView: WebView? = null

    constructor(activity: FragmentActivity, webView: WebView) : this() {
        this.activity = activity
        this.webView = webView
    }

    constructor(fragment: Fragment, webView: WebView) : this() {
        this.fragment = fragment
        this.webView = webView
    }

    private val apiManager = ApiManager()

    init {
        activity?.lifecycle?.addObserver(this)
        fragment?.lifecycle?.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner?, event: Lifecycle.Event?) {
        Logger.d(event?.name)

        when (event) {
            Lifecycle.Event.ON_CREATE -> {

            }

            Lifecycle.Event.ON_DESTROY -> {
                webView?.run {
                    setOnKeyListener(null)
                    webViewClient = null
                    webChromeClient = null
                    removeAllViews()
                    destroy()
                }
            }

            else -> {

            }
        }
    }

    /**
     *
     */
    fun handleUrl(url: Uri): Boolean {
        webView?.run {
            return apiManager.handleUrl(url, this)
        }
        return false
    }

    /**
     *
     */
    fun registerApi(api: AbsApi) = apiManager.registerApi(api)

    fun unRegister(fragment: Fragment){
        apiManager.unRegister(fragment)
    }
    fun unRegister(activity: Activity){
        apiManager.unRegister(activity)
    }

    /**
     *
     */
    fun setupWebView() {
        // 注册 API
        registerApi(EnvApi().also {
            activity?.run {
                it.registerActivity(this)
            }
            fragment?.run {
                it.registerFragment(this)
            }
        })
        HybridConfig.customApi.forEach {
            registerApi(it.also {
                activity?.run { it.registerActivity(this) }
                fragment?.run { it.registerFragment(this) }
            })
        }

        // 配置 WebView
        webView?.run {
            setupWebClient(this)
            setupWebSettings(this.settings)
        }

    }



    /**
     * 设置 WebClient
     */
    private fun setupWebClient(webView: WebView) {
        webView.webViewClient = HybridWebViewClient(hybrid = this)
        webView.webChromeClient = HybridWebChromeClient(activity, fragment)
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

        val dir = when {
            activity != null -> activity!!.getDir("geolocation", Context.MODE_PRIVATE).path
            fragment != null -> fragment!!.requireActivity().getDir(
                "geolocation",
                Context.MODE_PRIVATE
            ).path
            else -> ""
        }


        settings.setGeolocationDatabasePath(dir)
    }

}