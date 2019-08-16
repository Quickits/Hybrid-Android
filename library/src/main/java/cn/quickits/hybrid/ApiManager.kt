package cn.quickits.hybrid

import android.app.Activity
import android.net.Uri
import android.webkit.WebView
import androidx.collection.ArrayMap
import androidx.fragment.app.Fragment
import cn.quickits.hybrid.annotation.APIPath
import cn.quickits.hybrid.api.AbsApi


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-15 14:42
 **/
class ApiManager {

    private val apiPool = ArrayMap<String, AbsApi>()

    fun handleUrl(url: Uri, webView: WebView): Boolean {
        println("handleUrl: $url")
        return getApi(url)?.handleUrl(url, webView) ?: false
    }

    fun registerApi(absApi: AbsApi) {
        if (absApi.javaClass.isAnnotationPresent(APIPath::class.java)) {
            val apiPath = absApi.javaClass.getAnnotation(APIPath::class.java) ?: return
            apiPool[apiPath.path] = absApi
        } else {

        }
    }

    fun unRegister(activity: Activity){
        apiPool.forEach { (_, absApi) ->
            absApi.unRegisterActivity(activity)
        }
    }

    fun unRegister(fragment: Fragment){
        apiPool.forEach { (_, absApi) ->
            absApi.unRegisterFragment(fragment)
        }
    }

    private fun getApi(url: Uri): AbsApi? = apiPool["${url.scheme}://${url.host}"]

    private fun g(url: Uri): AbsApi? = apiPool["${url.scheme}://${url.host}"]

}