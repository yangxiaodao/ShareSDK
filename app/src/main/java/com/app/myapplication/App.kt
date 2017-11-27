package com.app.myapplication

import android.app.Application
import com.library.share.ShareSDK

/**
 * Created by jin on 2017/11/27.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val platform = mutableMapOf<Int, Map<String, String>>()
        val platformWx = mutableMapOf<String, String>()
        platformWx.put(ShareSDK.PLATFORM_APP_ID, "wxd930ea5d5a258f4f")
        platform.put(ShareSDK.SHARE_PLATFORM_WX, platformWx)
        ShareSDK.setPlatform(this, platform)
    }
}