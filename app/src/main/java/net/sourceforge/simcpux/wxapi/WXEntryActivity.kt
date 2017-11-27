package net.sourceforge.simcpux.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.library.share.ShareSDK
import com.library.share.WxShare
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

/**
 * Created by jin on 2017/11/23.
 */
class WXEntryActivity : Activity(), IWXAPIEventHandler {

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
    }

    override fun onReq(p0: BaseReq?) {

    }
}