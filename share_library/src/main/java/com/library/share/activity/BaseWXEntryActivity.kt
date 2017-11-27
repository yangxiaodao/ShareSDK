package com.library.share.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.library.share.R
import com.library.share.ShareSDK
import com.library.share.WxShare
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

open class BaseWXEntryActivity : Activity(), IWXAPIEventHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WxShare.sWxApi!!.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        WxShare.sWxApi!!.handleIntent(intent, this)
    }

    override fun onResp(resp: BaseResp) {
        ShareSDK.handleWxResponse(resp)
        finish()
        overridePendingTransition(R.anim.page_close_enter, R.anim.page_close_exit)
    }

    override fun onReq(p0: BaseReq?) {

    }
}