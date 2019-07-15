package cn.quickits.hybrid.api

import android.net.Uri
import android.util.ArrayMap
import android.webkit.WebView
import cn.quickits.hybrid.annotation.APIEndpoint
import cn.quickits.hybrid.api.collection.Endpoint
import cn.quickits.hybrid.dto.Result
import cn.quickits.hybrid.util.Logger
import com.google.gson.Gson


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-12 11:34
 **/
abstract class AbsApi {

    private val endpoints = ArrayMap<String, Endpoint>()

    fun handleUrl(url: Uri, webView: WebView): Boolean {
        val method = url.pathSegments[0]
        val param = url.getQueryParameter(KEY_PARAMETER)
        val reqSn = url.getQueryParameter(KEY_REQUEST_SN)

        Logger.d(method)
        Logger.d(param)
        Logger.d(reqSn)

        val endpoint = getEndpoint(method)

        if (!reqSn.isNullOrEmpty()) {
            val result = endpoint?.invoke(param)

            webView.loadUrl("javascript:QuickitsHybrid.invoke('${Gson().toJson(Result(result, reqSn))}');")
        }

        return true
    }

    private fun getEndpoint(method: String): Endpoint? {
        return endpoints[method] ?: generateEndpoint(method)
    }

    private fun generateEndpoint(methodName: String): Endpoint? {
        var endpoint: Endpoint? = null

        javaClass.methods.forEach {
            if (it.isAnnotationPresent(APIEndpoint::class.java)) {
                if (it.name == methodName) {

                    it.parameterTypes

                    endpoint = Endpoint(this, it)

                    return@forEach
                }
            }
        }

        return endpoint
    }

    companion object {
        private const val KEY_PARAMETER = "param"
        private const val KEY_REQUEST_SN = "req_sn"
    }

}