package cn.quickits.hybrid.api

import cn.quickits.hybrid.annotation.APIEndpoint
import cn.quickits.hybrid.annotation.APIPath


/**
 * @program: Hybrid-Android
 * @description:
 * @author: gavinliu
 * @create: 2019-07-12 11:30
 **/
@APIPath(path = "qhybrid://cn.quickits.hybrid.api.EnvApi")
class EnvApi : AbsApi() {

    @APIEndpoint
    fun getVersion(): String {
        return "V1.2.3"
    }

}