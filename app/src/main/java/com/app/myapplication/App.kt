package com.app.myapplication

import android.app.Application
import com.library.share.ShareConfiguration
import com.library.share.ShareSDK

/**
 * Created by jin on 2017/11/27.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val configuration = ShareConfiguration.Builder()
                .wechat("wxd930ea5d5a258f4f")
                .build()
        ShareSDK.config(this, configuration)
    }
}