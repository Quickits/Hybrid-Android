package cn.quickits.hybrid.sample

import cn.quickits.hybrid.annotation.APIEndpoint
import cn.quickits.hybrid.annotation.APIPath
import cn.quickits.hybrid.api.AbsApi
import com.hbbclub.storybook.util.AccountInfoManager

/**
 * @program: storybook_android

 * @author: gavinliu
 * @create: 2019-07-15 19:25
 **/
@APIPath(path = "hbbrid://com.hbbclub.hybrid.AuthHandler")
class AuthHandler : AbsApi() {
    @APIEndpoint
    fun getToken(): String {
        return AccountInfoManager.get.mAccountInfo.userToken.token ?: ""
    }
}