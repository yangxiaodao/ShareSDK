package com.library.share

/**
 * Created by jin on 2017/11/27.
 */
class ShareConfiguration {

    var wxID: String? = null

    class Builder {

        private var wxID: String? = null

        fun wechat(string: String): Builder {
            wxID = string
            return this
        }

        fun build(): ShareConfiguration {
            val configuration = ShareConfiguration()
            configuration.wxID = wxID
            return configuration
        }
    }
}