package cn.quickits.hybrid.api

import android.net.Uri


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-12 11:34
 **/
abstract class AbsAPI {

    open fun getId() = javaClass.name

    fun handleUrl(uri: Uri) {
        val host = uri.host
        val method = uri.pathSegments[0]
        val param = uri.getQueryParameter(KEY_PARAMETER)
        val req_sn = uri.getQueryParameter(KEY_REQUEST_SN)
    }

    companion object {
        private const val KEY_PARAMETER = "param"
        private const val KEY_REQUEST_SN = "req_sn"
    }

}