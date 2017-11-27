package com.library.share

import android.graphics.Bitmap
import com.library.share.model.ShareEntity
import com.library.share.model.ShareResponse
import com.library.share.util.WxUtil
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.*
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

object WxShare {

    private val THUMB_SIZE = 150

    private var sWx_ID: String? = null
    var sWxApi: IWXAPI? = null

    fun initWx(wxID: String) {
        sWx_ID = wxID
        sWxApi = WXAPIFactory.createWXAPI(ShareSDK.sContext, sWx_ID, true)
        sWxApi!!.registerApp(sWx_ID)
    }

    fun share(shareEntity: ShareEntity) {
        var wxMediaMessage = WXMediaMessage()
        when (shareEntity.shareType) {
            ShareSDK.SHARE_TYPE_TEXT -> {
                var mediaObject = WXTextObject()
                mediaObject.text = shareEntity.content
                wxMediaMessage.mediaObject = mediaObject
                wxMediaMessage.description = shareEntity.content
            }
            ShareSDK.SHARE_TYPE_IMAGE ->{
                var mediaObject = WXImageObject(shareEntity.bitmap)
                wxMediaMessage.mediaObject = mediaObject
                var thumbBitmap = Bitmap.createScaledBitmap(shareEntity.bitmap, THUMB_SIZE, THUMB_SIZE,true)
                shareEntity.bitmap?.recycle()
                wxMediaMessage.thumbData = WxUtil.bmpToByteArray(thumbBitmap, true)
            }
            ShareSDK.SHARE_TYPE_WEB -> {
                var mediaObject = WXWebpageObject()
                mediaObject.webpageUrl = shareEntity.webPageUrl
                wxMediaMessage.mediaObject = mediaObject
                wxMediaMessage.title = shareEntity.title
                wxMediaMessage.description = shareEntity.description
                wxMediaMessage.thumbData = WxUtil.bmpToByteArray(shareEntity.bitmap, true)
            }
        }
        val req = SendMessageToWX.Req()
        req.transaction = System.currentTimeMillis().toString()
        req.message = wxMediaMessage
        req.scene = if (shareEntity.shareChannel == ShareSDK.SHARE_PLATFORM_WX_TIME_LINE)
            SendMessageToWX.Req.WXSceneTimeline else
            SendMessageToWX.Req.WXSceneSession
        sWxApi!!.sendReq(req)
    }

    fun handleWxResponse(resp: BaseResp): ShareResponse {
        return when (resp.errCode) {
            BaseResp.ErrCode.ERR_OK -> ShareResponse(ShareSDK.SHARE_RESPONSE_SUCCESS, "分享成功！")
            BaseResp.ErrCode.ERR_USER_CANCEL -> ShareResponse(ShareSDK.SHARE_RESPONSE_CANCEL, "取消分享！")
            else -> ShareResponse(ShareSDK.SHARE_RESPONSE_ERROR, "分享失败！")
        }
    }
}