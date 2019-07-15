package cn.quickits.hybrid

import cn.quickits.hybrid.api.AbsApi


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-15 17:30
 **/
object HybridConfig {

    internal val customApi: ArrayList<AbsApi> = arrayListOf()

    fun addCustomApi(absApi: AbsApi): HybridConfig {
        customApi.add(absApi)
        return this
    }

}