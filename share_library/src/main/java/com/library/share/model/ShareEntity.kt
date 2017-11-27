package com.library.share.model

import android.graphics.Bitmap

data class ShareEntity(
        var content: String?,
        var bitmap: Bitmap?,
        var webPageUrl: String?,
        var title: String?,
        var description: String?,
        var shareType: Int,
        var shareChannel: Int
)