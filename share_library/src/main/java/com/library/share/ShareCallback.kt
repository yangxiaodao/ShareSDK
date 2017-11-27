package com.library.share

import com.library.share.model.ShareResponse

interface ShareCallback {
    fun onResponse(response: ShareResponse)
}