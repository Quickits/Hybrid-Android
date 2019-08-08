package com.hbbclub.storybook.hybrid

import cn.quickits.hybrid.annotation.APIEndpoint
import cn.quickits.hybrid.annotation.APIParam
import cn.quickits.hybrid.annotation.APIPath
import cn.quickits.hybrid.api.AbsApi
import com.blankj.utilcode.util.LogUtils
import com.hbbclub.storybook.HybridActivity
import com.hbbclub.storybook.mvvm.data.dto.Share

/**
 * @program: storybook_android

 * @author: gavinliu
 * @create: 2019-07-15 19:25
 **/
@APIPath(path = "hbbrid://com.hbbclub.hybrid.UIHandler")
class UIHandler : AbsApi() {
    @APIEndpoint
    fun createTitleButton(@APIParam("btnLabel") btnLabel:String,
                          @APIParam("action") action:String,
                          @APIParam("data") data:String) {
        if (activity !is HybridActivity){
            return
        }
        (activity as HybridActivity).addButton(btnLabel,action,data)
    }
}