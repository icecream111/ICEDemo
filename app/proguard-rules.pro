# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#==============rxjava------------
-dontwarn okio.**
-dontwarn javax.annotation.**
#==============rxjava------------
#****************************************************
#==========okhttp---------------
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
#==========okhttp---------------

# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\admin\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#-ignorewarnings
-keepattributes *Annotation*
-keep class java.**{*;}
-dontwarn java.**
-keep class android.** {*;}
-dontwarn android.**
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }

#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}
#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.**{*;}
-dontwarn com.squareup.wire.**
-keep class com.squareup.wire.** {*;}
#okio
-dontwarn okio.**
-keep class okio.**{*;}
-dontwarn okio.**

#支付宝
-dontwarn alibaba.**
-keep class com.alibaba.**{*;}
-keep class com.taobao.**{*;}
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
#FastJson
-keepattributes InnerClasses,Signature
#Gson
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

#这句非常重要，主要是滤掉 com.bgb.scan.model包下的所有.class文件不进行混淆编译
-keep class com.bgb.scan.model.** {*;}

-keep class com.google.gson.** { *;}
-keep public class com.google.gson.** {public private protected *;}
-keep public class com.project.mocha_patient.login.SignResponseData { private *; }

-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**

#umeng 推送
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**
-dontwarn com.umeng.analytics.**

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}
-keep class com.umeng.analytics.** {*;}

-keep public class **.R$*{
   public static final int *;
}

#blankj.utilcode工具类
-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**

#greendao
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use Rx:
-dontwarn org.greenrobot.greendao.rx.**
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#项目里面包含的包也不能混淆
-keep class com.baidu.** {*;}
-dontwarn com.baidu.**

-keep class vi.com.gdi.bgl.android.java.** {*;}
-dontwarn vi.com.gdi.bgl.android.java.**

-dontwarn com.ut.mini.**

-keep class com.umeng.message.protobuffer.* {
	 public <fields>;
         public <methods>;
}

-keep class com.umeng.message.* {
	 public <fields>;
         public <methods>;
}

-keep class org.android.agoo.impl.* {
	 public <fields>;
         public <methods>;
}

-keep class org.android.agoo.service.* {*;}

-keep class org.android.spdy.**{*;}


#如果compileSdkVersion为23，请添加以下混淆代码
-dontwarn org.apache.http.**
-dontwarn android.webkit.**
-keep class org.apache.http.** { *; }
-keep class org.apache.commons.codec.** { *; }
-keep class org.apache.commons.logging.** { *; }
-keep class android.net.compatibility.** { *; }
-keep class android.net.http.** { *; }

-keep class net.sourceforge.pinyin4j.**{*;}
-dontwarn com.hp.hpl.sparta.**

-keep class PinyinjAppletDemo.**{*;}
-dontwarn demo.**
-keep class org.apache.**{*;}
-dontwarn org.apache.**


#shareSDK的混淆
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-keep class com.mob.**{*;}
-dontwarn com.mob.**
-dontwarn cn.sharesdk.**
-dontwarn **.R$*
-keep class m.framework.**{*;}
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class com.mob.tools.utils
-keep class com.xxx.share.onekey.theme.classic.EditPage


-keep class com.bm.oa.utils.SmileUtils { *; }
-dontwarn com.bm.oa.utils.SmileUtils

#=========================umeng 统计=====================
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keep public class com.bm.oa.R$*{
public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#=========================umeng 统计=====================

#=========================阿里路由框架=====================
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider
#=========================阿里路由框架=====================


#所有的实体类
-keep public class com.ice.retrofitrxjavademo.bean.**{*;}


-keepattributes *JavascriptInterface*
-keepclassmembers class com.bm.oa.framework.device.MyJavaScriptInerface{public *;}
-keepnames class com.bm.oa.framework.device.SalePriceDetailsActivity$MyJavaScriptInterface{
  *;
}
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
 }


 #fastjson

 -dontwarn com.alibaba.fastjson.**
 -dontskipnonpubliclibraryclassmembers
 -dontskipnonpubliclibraryclasses

 -keep class com.alibaba.fastjson.**{*;}
 -keep class * implements java.io.Serializable { *; }

 -keepattributes *Annotation
 -keepattributes Signature


 #-optimizationpasses 7
 #-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
 -dontoptimize
 -dontusemixedcaseclassnames
 -verbose
 -dontskipnonpubliclibraryclasses
 -dontskipnonpubliclibraryclassmembers
 -dontwarn dalvik.**
 #-overloadaggressively

 #@proguard_debug_start
 # ------------------ Keep LineNumbers and properties ---------------- #
 -keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
 -renamesourcefileattribute TbsSdkJava
 -keepattributes SourceFile,LineNumberTable
 #@proguard_debug_end

 # --------------------------------------------------------------------------
 # Addidional for x5.sdk classes for apps

 -keep class com.tencent.smtt.export.external.**{
     *;
 }

 -keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
 	*;
 }

 -keep class com.tencent.smtt.sdk.CacheManager {
 	public *;
 }

 -keep class com.tencent.smtt.sdk.CookieManager {
 	public *;
 }

 -keep class com.tencent.smtt.sdk.WebHistoryItem {
 	public *;
 }

 -keep class com.tencent.smtt.sdk.WebViewDatabase {
 	public *;
 }

 -keep class com.tencent.smtt.sdk.WebBackForwardList {
 	public *;
 }

 -keep public class com.tencent.smtt.sdk.WebView {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
 	public static final <fields>;
 	public java.lang.String getExtra();
 	public int getType();
 }

 -keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebView$PictureListener {
 	public <fields>;
 	public <methods>;
 }


 -keepattributes InnerClasses

 -keep public enum com.tencent.smtt.sdk.WebSettings$** {
     *;
 }

 -keep public enum com.tencent.smtt.sdk.QbSdk$** {
     *;
 }

 -keep public class com.tencent.smtt.sdk.WebSettings {
     public *;
 }


 -keepattributes Signature
 -keep public class com.tencent.smtt.sdk.ValueCallback {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebViewClient {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.DownloadListener {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebChromeClient {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {
 	public <fields>;
 	public <methods>;
 }

 -keep class com.tencent.smtt.sdk.SystemWebChromeClient{
 	public *;
 }
 # 1. extension interfaces should be apparent
 -keep public class com.tencent.smtt.export.external.extension.interfaces.* {
 	public protected *;
 }

 # 2. interfaces should be apparent
 -keep public class com.tencent.smtt.export.external.interfaces.* {
 	public protected *;
 }

 -keep public class com.tencent.smtt.sdk.WebViewCallbackClient {
 	public protected *;
 }

 -keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebIconDatabase {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebStorage {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.DownloadListener {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.QbSdk {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.CookieSyncManager {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.Tbs* {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.utils.LogFileUtils {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.utils.TbsLog {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.utils.TbsLogClient {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.CookieSyncManager {
 	public <fields>;
 	public <methods>;
 }

 # Added for game demos
 -keep public class com.tencent.smtt.sdk.TBSGamePlayer {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.utils.Apn {
 	public <fields>;
 	public <methods>;
 }
 # end


 -keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
 	public <fields>;
 	public <methods>;
 }

 -keep class MTT.ThirdAppInfoNew {
 	*;
 }

 -keep class com.tencent.mtt.MttTraceEvent {
 	*;
 }

 # Game related
 -keep public class com.tencent.smtt.gamesdk.* {
 	public protected *;
 }

 -keep public class com.tencent.smtt.sdk.TBSGameBooter {
         public <fields>;
         public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {
 	public protected *;
 }

 -keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {
 	public protected *;
 }

 -keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {
 	public *;
 }
 #---------------------------------------------------------------------------
#=========================android M动态权限获取=============================
-keepclassmembers class ** {
    @com.yanzhenjie.permission.PermissionYes <methods>;
}
-keepclassmembers class ** {
    @com.yanzhenjie.permission.PermissionNo <methods>;
}
#=========================android M动态权限获取=============================

-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-dontwarn org.mockito.**
-dontwarn sun.reflect.**
-dontwarn android.test.**

-keepattributes Signature

-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep interface com.google.gson.stream.** { *; }
-keep class com.google.protobuf.** { *; }

-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Package path for GSON models
#-keep class com.threadflip.android.api.** { *; }
#-keep interface com.threadflip.android.api.** { *; }
#-keep class com.google.gson.examples.android.model.** { *; }

#============eventBus=========
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
#============eventBus=========
#============JDAddress 里面引用的一个数据库dbflow=======
-dontwarn com.raizlabs.android.dbflow.**
-keep class com.raizlabs.android.dbflow.**{ *; }
#============JDAddress=======





 #------------------  下方是android平台自带的排除项，这里不要动         ----------------

 -keep public class * extends android.app.Activity{
 	public <fields>;
 	public <methods>;
 }
 -keep public class * extends android.app.Application{
 	public <fields>;
 	public <methods>;
 }
 -keep public class * extends android.app.Service
 -keep public class * extends android.content.BroadcastReceiver
 -keep public class * extends android.content.ContentProvider
 -keep public class * extends android.app.backup.BackupAgentHelper
 -keep public class * extends android.preference.Preference

 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 -keepclasseswithmembers class * {
 	public <init>(android.content.Context, android.util.AttributeSet);
 }

 -keepclasseswithmembers class * {
 	public <init>(android.content.Context, android.util.AttributeSet, int);
 }

 -keepattributes *Annotation*

 -keepclasseswithmembernames class *{
 	native <methods>;
 }

 -keep class * implements android.os.Parcelable {
   public static final android.os.Parcelable$Creator *;
 }

 #------------------  下方是共性的排除项目         ----------------
 # 方法名中含有“JNI”字符的，认定是Java Native Interface方法，自动排除
 # 方法名中含有“JRI”字符的，认定是Java Reflection Interface方法，自动排除

 -keepclasseswithmembers class * {
     ... *JNI*(...);
 }

 -keepclasseswithmembernames class * {
 	... *JRI*(...);
 }

 -keep class **JNI* {*;}


