package com.library.share

import android.annotation.SuppressLint
import android.content.Context
import com.library.share.model.ShareEntity
import com.library.share.model.ShareResponse
import com.tencent.mm.opensdk.modelbase.BaseResp

@SuppressLint("StaticFieldLeak")
object ShareSDK {

    var PLATFORM_APP_ID = "AppID"

    var SHARE_PLATFORM_WX = 1
    var SHARE_PLATFORM_WX_SESSION = 1
    var SHARE_PLATFORM_WX_TIME_LINE = 2

    var SHARE_TYPE_TEXT = 1
    var SHARE_TYPE_IMAGE = 2
    var SHARE_TYPE_WEB = 3

    var SHARE_RESPONSE_SUCCESS = 1
    var SHARE_RESPONSE_CANCEL = 2
    var SHARE_RESPONSE_UNINSTALL = 3
    var SHARE_RESPONSE_ERROR = 4

    var sContext: Context? = null

    private var sCallback: ShareCallback? = null

    fun setPlatform(context: Context, platform: Map<Int, Map<String, String>>) {
        sContext = context
        //wechat
        var platformData = platform[SHARE_PLATFORM_WX]
        platformData?.let {
            val wxAppID = platformData[PLATFORM_APP_ID]
            wxAppID?.let { WxShare.initWx(wxAppID) }
        }
    }

    fun share(shareEntity: ShareEntity) {
        when (shareEntity.shareChannel) {
            SHARE_PLATFORM_WX_SESSION, SHARE_PLATFORM_WX_TIME_LINE -> {
                if (WxShare.sWxApi!!.isWXAppInstalled) {
                    WxShare.share(shareEntity)
                } else {
                    sCallback?.onResponse(ShareResponse(SHARE_RESPONSE_UNINSTALL, "未安装微信"))
                }
            }
        }
    }

    fun setShareCallback(callback: ShareCallback) {
        sCallback = callback
    }

    fun handleWxResponse(resp: BaseResp) {
        sCallback?.onResponse(WxShare.handleWxResponse(resp))
    }

}