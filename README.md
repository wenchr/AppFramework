# AppFramework
a android app framework

# Features
* [x] Network base httpclient
* [x] DataCache

# Overview

# Installation
allprojects {
    repositories {
        maven { url "https://www.jitpack.io" }
    }
}

buildscript {
    repositories {
        mavenCentral()
        maven { url "https://www.jitpack.io" }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.5.0'
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: '*.jar')
    compile 'com.github.yangzc:AppFramework:v1.0.0'
}

# Project structure
```shell
.
├── AndroidManifest.xml
├── AppFramework.iml
├── README.md
├── assets
│   ├── ids_framework.xml
│   ├── jens_listview.html
│   └── js
│       └── jquery.js
├── build.gradle
├── doc
│   └── 知识印象Android应用架构.graffle
├── gen
│   └── com
│       └── hyena
│           └── framework
│               ├── BuildConfig.java
│               └── R.java
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── libs
│   ├── android-support-v4.jar
│   ├── android-support-v7-recyclerview.jar
│   ├── httpmime-4.1.2.jar
│   ├── nineoldandroids.jar
│   ├── tagsoup-1.2.jar
│   └── universal-image-loader-1.9.4.jar
├── lint.xml
├── manifest.mf
├── pom.xml
├── proguard-project.txt
├── project.properties
├── release.sh
├── res
│   ├── drawable-hdpi
│   │   └── ic_launcher.png
│   ├── drawable-mdpi
│   │   └── ic_launcher.png
│   ├── drawable-xhdpi
│   │   └── ic_launcher.png
│   └── values
│       ├── strings.xml
│       └── styles.xml
├── resources
│   └── META-INF
│       └── services
│           └── javax.annotation.processing.Processor
├── src
│   └── com
│       └── hyena
│           └── framework
│               ├── animation
│               │   ├── CGLView.java
│               │   ├── CLayer.java
│               │   ├── CScene.java
│               │   ├── CSurfaceView.java
│               │   ├── Director.java
│               │   ├── EngineConfig.java
│               │   ├── RenderView.java
│               │   ├── action
│               │   │   ├── CAlphaToAction.java
│               │   │   ├── CDelayAction.java
│               │   │   ├── CFrameAction.java
│               │   │   ├── CMethodAction.java
│               │   │   ├── CMoveToAction.java
│               │   │   ├── CRotateToAction.java
│               │   │   ├── CScaleToAction.java
│               │   │   ├── CSequenceAction.java
│               │   │   └── base
│               │   │       ├── CAction.java
│               │   │       ├── CFiniteTimeAction.java
│               │   │       ├── CIntervalAction.java
│               │   │       └── CRepeatAction.java
│               │   ├── nodes
│               │   │   ├── CScoreNode.java
│               │   │   └── CTextNode.java
│               │   ├── particle
│               │   │   ├── CMelodyStyle.java
│               │   │   ├── CParticle.java
│               │   │   ├── CParticleArrayList.java
│               │   │   ├── CParticleSystem.java
│               │   │   └── CSnowSytle.java
│               │   ├── sprite
│               │   │   ├── CActionNode.java
│               │   │   ├── CNode.java
│               │   │   ├── CPoint.java
│               │   │   └── CSprite.java
│               │   ├── texture
│               │   │   ├── BitmapManager.java
│               │   │   ├── CBaseTexture.java
│               │   │   └── CTexture.java
│               │   └── utils
│               │       ├── AnimUtils.java
│               │       ├── BitmapUtils.java
│               │       ├── StreamUtils.java
│               │       └── UIUtils.java
│               ├── annotation
│               │   ├── AnnotationProcessor.java
│               │   ├── AttachViewId.java
│               │   ├── LayoutAnimation.java
│               │   └── SystemService.java
│               ├── app
│               │   ├── NavigateController.java
│               │   ├── activity
│               │   │   ├── BaseActivity.java
│               │   │   ├── NavigateActivity.java
│               │   │   └── bean
│               │   │       └── BasicUserInfo.java
│               │   ├── adapter
│               │   │   ├── MultiTypeAdapter.java
│               │   │   ├── PiecesAdapter.java
│               │   │   ├── SimplePagerAdapter.java
│               │   │   ├── SimpleStatePagerAdaper.java
│               │   │   ├── SingleTypeAdapter.java
│               │   │   └── SingleViewAdapter.java
│               │   ├── coretext
│               │   │   ├── FillInMovementMethod.java
│               │   │   ├── Html.java
│               │   │   ├── span
│               │   │   │   ├── BaseSpan.java
│               │   │   │   ├── ClickableImageSpan.java
│               │   │   │   ├── FillInSpan.java
│               │   │   │   ├── FractionSpan.java
│               │   │   │   ├── SingleFillInSpan.java
│               │   │   │   └── VerticalImageSpan.java
│               │   │   └── utils
│               │   │       ├── DefaultTagHandler.java
│               │   │       └── SpannableUtils.java
│               │   ├── fragment
│               │   │   ├── AnimationFragment.java
│               │   │   ├── BaseFragment.java
│               │   │   ├── BaseSubFragment.java
│               │   │   ├── BaseUIFragment.java
│               │   │   ├── BaseUIFragmentHelper.java
│               │   │   ├── BaseWebFragment.java
│               │   │   ├── DialogFragment.java
│               │   │   ├── HSlidingBackFragment.java
│               │   │   ├── ListFragment.java
│               │   │   ├── SafeFragment.java
│               │   │   ├── UIViewFactory.java
│               │   │   ├── ViewBuilder.java
│               │   │   └── bean
│               │   │       ├── MenuItem.java
│               │   │       └── UrlModelPair.java
│               │   └── widget
│               │       ├── AccuracGridView.java
│               │       ├── AccuracListView.java
│               │       ├── BaseUIRootLayout.java
│               │       ├── CircleHintView.java
│               │       ├── CommonEmptyView.java
│               │       ├── CommonLoadingView.java
│               │       ├── CommonTabBar.java
│               │       ├── CommonTitleBar.java
│               │       ├── DefaultUIViewBuilder.java
│               │       ├── DragablePanel.java
│               │       ├── EmptyView.java
│               │       ├── HSlidingPaneLayout.java
│               │       ├── HybirdWebListView.java
│               │       ├── HybirdWebView.java
│               │       ├── ListLoadingMoreFooter.java
│               │       ├── LoadMoreListView.java
│               │       ├── LoadingView.java
│               │       ├── MovieView.java
│               │       ├── NumberBoard.java
│               │       ├── SimpleRecycleView.java
│               │       ├── TitleBar.java
│               │       ├── URLDrawable.java
│               │       ├── WebChromeClientWrapper.java
│               │       └── WebViewClientWrapper.java
│               ├── audio
│               │   ├── MediaPlayServiceHelper.java
│               │   ├── MediaService.java
│               │   ├── MusicDir.java
│               │   ├── MusicPlayer.java
│               │   ├── StatusCode.java
│               │   ├── bean
│               │   │   └── Song.java
│               │   ├── codec
│               │   │   ├── Decoder.java
│               │   │   └── NativeMP3Decoder.java
│               │   └── player
│               │       ├── BasePlayer.java
│               │       ├── LocalPlayer.java
│               │       └── OnlinePlayer.java
│               ├── clientlog
│               │   ├── LogUtil.java
│               │   └── Logger.java
│               ├── config
│               │   └── FrameworkConfig.java
│               ├── database
│               │   ├── BaseDataBaseHelper.java
│               │   ├── BaseItem.java
│               │   ├── BaseTable.java
│               │   ├── DataBaseHelper.java
│               │   └── DataBaseManager.java
│               ├── datacache
│               │   ├── BaseObject.java
│               │   ├── DataAcquirer.java
│               │   ├── cache
│               │   │   ├── CacheEntry.java
│               │   │   ├── CacheExpiredException.java
│               │   │   ├── CacheUncachedException.java
│               │   │   ├── Cacheable.java
│               │   │   └── DataCache.java
│               │   ├── db
│               │   │   ├── DBHelper.java
│               │   │   ├── DataCacheItem.java
│               │   │   └── DataCacheTable.java
│               │   └── objects
│               │       ├── HashMap.java
│               │       ├── LinkedHashMap.java
│               │       ├── LruCache2.java
│               │       └── Objects.java
│               ├── debug
│               │   ├── DebugHelper.java
│               │   ├── DebugUtils.java
│               │   ├── InvokeHelper.java
│               │   └── observer
│               │       ├── BaseOnClickListener.java
│               │       └── BaseOnItemClickListener.java
│               ├── download
│               │   ├── DownloadManager.java
│               │   ├── Downloader.java
│               │   ├── Task.java
│               │   ├── db
│               │   │   ├── DownloadItem.java
│               │   │   └── DownloadTable.java
│               │   ├── exception
│               │   │   └── ExistTaskException.java
│               │   └── task
│               │       ├── TaskFactory.java
│               │       └── UrlTask.java
│               ├── error
│               │   ├── ErrorManager.java
│               │   └── ErrorMap.java
│               ├── network
│               │   ├── DefaultNetworkSensor.java
│               │   ├── HttpError.java
│               │   ├── HttpExecutor.java
│               │   ├── HttpListener.java
│               │   ├── HttpProvider.java
│               │   ├── HttpResult.java
│               │   ├── NetworkProvider.java
│               │   ├── NetworkSensor.java
│               │   ├── executor
│               │   │   ├── DefaultHttpExecutor.java
│               │   │   ├── EasySSLSocketFactory.java
│               │   │   ├── EasyX509TrustManager.java
│               │   │   ├── PreemptiveAuthorizer.java
│               │   │   └── SSLHttpClient.java
│               │   ├── listener
│               │   │   ├── CancelableListener.java
│               │   │   ├── DataHttpListener.java
│               │   │   ├── FileHttpListener.java
│               │   │   ├── JsonHttpListener.java
│               │   │   └── RandomFileHttpListener.java
│               │   └── utils
│               │       ├── EntityUtil.java
│               │       ├── HttpUtils.java
│               │       ├── MultiFileDownloadHelper.java
│               │       ├── SimpleFileUploader.java
│               │       └── UploaderManager.java
│               ├── security
│               │   ├── AESUtils.java
│               │   ├── Base64.java
│               │   ├── CRCUtil.java
│               │   └── MD5Util.java
│               ├── servcie
│               │   ├── BaseService.java
│               │   ├── BaseServiceManager.java
│               │   ├── IServiceManager.java
│               │   ├── ServiceProvider.java
│               │   ├── audio
│               │   │   ├── PlayerBusService.java
│               │   │   ├── PlayerBusServiceImpl.java
│               │   │   ├── PlayerBusServiceObserver.java
│               │   │   └── listener
│               │   │       ├── PlayStatusChangeListener.java
│               │   │       └── ProgressChangeListener.java
│               │   ├── bus
│               │   │   ├── BusService.java
│               │   │   ├── BusServiceImpl.java
│               │   │   └── IBusServiceStatusListener.java
│               │   └── debug
│               │       ├── DebugModeListener.java
│               │       ├── DebugServerObserver.java
│               │       ├── DebugService.java
│               │       └── DebugServiceImpl.java
│               └── utils
│                   ├── AdbUtils.java
│                   ├── AnimationUtils.java
│                   ├── AppPreferences.java
│                   ├── BadgeUtil.java
│                   ├── BaseApp.java
│                   ├── BaseFileUtils.java
│                   ├── CodingUtils.java
│                   ├── CollectionUtil.java
│                   ├── CrashHelper.java
│                   ├── FileUtils.java
│                   ├── HttpHelper.java
│                   ├── ImageFetcher.java
│                   ├── ImageUtils.java
│                   ├── MathUtils.java
│                   ├── MsgCenter.java
│                   ├── NetworkHelpers.java
│                   ├── ProcessUtils.java
│                   ├── ResourceUtils.java
│                   ├── RoundDisplayer.java
│                   ├── ToastUtils.java
│                   ├── UIUtils.java
│                   ├── UiThreadHandler.java
│                   └── VersionUtils.java
└── tree.txt

70 directories, 246 files
```

# Acknowledgements
- 

