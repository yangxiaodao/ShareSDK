# ShareSDK

轻量级的分享SDK，目前支持平台：
* WeChat


## How to use

### 配置share sdk

    class App : Application() {

        override fun onCreate() {
            super.onCreate()
            val configuration = ShareConfiguration.Builder()
                    .wechat("wxd930ea5d5a258f4f")
                    .build()
            ShareSDK.config(this, configuration)
        }
    }