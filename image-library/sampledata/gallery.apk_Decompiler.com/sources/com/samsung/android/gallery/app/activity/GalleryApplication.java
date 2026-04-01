package com.samsung.android.gallery.app.activity;

import C3.i;
import C3.k;
import C3.l;
import android.app.Application;
import android.content.Context;
import com.samsung.android.gallery.bixby.bixby.handler.BixbyProxy;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.scpm.ScpmManager;
import com.samsung.android.gallery.module.debugger.PerformanceTracker;
import com.samsung.android.gallery.module.graphics.BitmapPool;
import com.samsung.android.gallery.module.graphics.ImageDecoder;
import com.samsung.android.gallery.module.graphics.QuramBitmapFactory;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.nondestruction.NonDestructionManager;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.exception.ApplicationExceptionHandler;
import com.samsung.android.gallery.support.exception.DiagMonLogger;
import com.samsung.android.gallery.support.exception.GalleryUncaughtExceptionHandler;
import com.samsung.android.gallery.support.providers.GmpCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MemoryUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.cache.ViewPoolCache;
import java.io.File;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryApplication extends Application {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DataHolder {
        static final int MEMORY_LOG_LEVEL;

        static {
            int i2;
            if (SdkConfig.atLeast(SdkConfig.SEM.V)) {
                i2 = 20;
            } else {
                i2 = 15;
            }
            MEMORY_LOG_LEVEL = i2;
        }
    }

    static {
        Trace.mark("GalleryApplication");
        long currentTimeMillis = System.currentTimeMillis();
        Log.initPLog(currentTimeMillis);
        Trace.beginSection("APP_GalleryApplication newInstance");
        PerformanceTracker.setAppCreateStart(currentTimeMillis);
        new Thread(new i(1)).start();
    }

    public GalleryApplication() {
        Trace.endSection();
    }

    private boolean initAppOnBg(Context context) {
        Trace.beginSection("initAppOnBg");
        long currentTimeMillis = System.currentTimeMillis();
        initCacheManager();
        Log.d("GalleryApplication", "initAppOnBg cache ready +" + (System.currentTimeMillis() - currentTimeMillis));
        BixbyProxy.getInstance().init(context);
        if (PocFeatures.isEnabled(PocFeatures.GmpLocOnly) || PocFeatures.isEnabled(PocFeatures.GmpAll)) {
            GmpCompat.scheduleDataSync(context, false);
        }
        if (SdkConfig.range(SdkConfig.SEM.U, SdkConfig.SEM.B) && Features.isEnabled(Features.IS_SEP_LITE)) {
            QuramBitmapFactory.isEnabled();
        }
        Trace.endSection();
        return true;
    }

    /* access modifiers changed from: private */
    public static void initAppWoContext() {
        Trace.beginSection("initAppWoContext");
        long currentTimeMillis = System.currentTimeMillis();
        int semVersion = SdkConfig.getSemVersion();
        ThumbnailLoader.getInstance();
        ThreadPool.getInstance();
        ThreadUtil.getBgThread();
        BitmapPool.getInstance().init();
        ViewPoolCache.getInstance().init();
        TimeUtil.updateToday();
        Log.d("GalleryApplication", "initAppWoContext (" + semVersion + ") +" + (System.currentTimeMillis() - currentTimeMillis));
        Trace.endSection();
    }

    private void initCacheManager() {
        boolean z;
        File cacheDir = getCacheDir();
        if (cacheDir != null) {
            if (!LocationKey.isTimeline(LocationKey.getCurrentLocation()) || !PreferenceCache.LastUseSmallThumbKind.getBoolean()) {
                z = false;
            } else {
                z = true;
            }
            CacheManager.getInstance().initialize(CacheManager.getSimpleOptionsWithDiskOnly(cacheDir.getPath(), z));
        }
    }

    private boolean initHeavyApiOnBg(Context context) {
        Trace.beginSection("initHeavyApiOnBg");
        long currentTimeMillis = System.currentTimeMillis();
        AnalyticsLogger.getInstance().initialize(this);
        if (AnalyticsLogger.USER_RELEASE) {
            GalleryUncaughtExceptionHandler.getInstance().setDiagmon(new DiagMonLogger().init(context));
        }
        Features.isEnabled(Features.SUPPORT_CLUSTER_TABLE);
        NonDestructionManager.getInstance();
        ThreadUtil.postOnBgThreadDelayed(new l(context, 0), 1000);
        Log.d("GalleryApplication", "initHeavyApiOnBg +" + (System.currentTimeMillis() - currentTimeMillis));
        Trace.endSection();
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$initHeavyApiOnBg$2(Context context) {
        SamsungCloudCompat.checkSamsungCloud2(context);
        ScpmManager.getInstance().init(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onCreate$0(Context context, ThreadPool.JobContext jobContext) {
        return Boolean.valueOf(initAppOnBg(context));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onCreate$1(Context context, ThreadPool.JobContext jobContext) {
        return Boolean.valueOf(initHeavyApiOnBg(context));
    }

    public void onCreate() {
        Trace.beginSection("APP_onCreate App");
        Trace.beginSection("APP_super.onCreate");
        super.onCreate();
        Trace.endSection();
        long currentTimeMillis = System.currentTimeMillis();
        Context applicationContext = getApplicationContext();
        AppResources.setAppContext(applicationContext);
        Blackboard.getApplicationInstance().publish("data://app_context", applicationContext);
        FileUtils.initialize(applicationContext);
        ThreadPool.getInstance().submit(new k(this, applicationContext, 0));
        ThreadPool.getInstance().submit(new k(this, applicationContext, 1));
        Thread.setDefaultUncaughtExceptionHandler(GalleryUncaughtExceptionHandler.getInstance());
        GalleryUncaughtExceptionHandler.getInstance().addExceptionHandler(new ApplicationExceptionHandler());
        GalleryUncaughtExceptionHandler.getInstance().setSaveDumpRun(new i(2));
        PerformanceTracker.setAppCreateEnd(System.currentTimeMillis());
        Log.d("GalleryApplication", "GalleryApplication@" + Integer.toHexString(applicationContext.hashCode()) + " onCreate +" + (System.currentTimeMillis() - currentTimeMillis));
        Trace.endSection();
    }

    public void onLowMemory() {
        long currentTimeMillis = System.currentTimeMillis();
        String dumpProcessMemoryStats = MemoryUtils.dumpProcessMemoryStats();
        Log.i("GalleryApplication", "onLowMemory +" + (System.currentTimeMillis() - currentTimeMillis) + "\n" + dumpProcessMemoryStats);
        super.onLowMemory();
    }

    public void onTrimMemory(int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        super.onTrimMemory(i2);
        if (i2 >= 40) {
            ThumbnailLoader.getInstance().trimMemory(i2);
            BitmapPool.getInstance().clear();
            ImageDecoder.clear();
        }
        long cachedPssMegaBytes = MemoryUtils.getCachedPssMegaBytes();
        if (cachedPssMegaBytes <= 1000 || i2 != DataHolder.MEMORY_LOG_LEVEL) {
            Log.i("GalleryApplication", "onTrimMemory(" + i2 + ") " + cachedPssMegaBytes + "MB +" + (System.currentTimeMillis() - currentTimeMillis));
            return;
        }
        Log.w("GalleryApplication", "onTrimMemory(" + i2 + ") " + cachedPssMegaBytes + "MB +" + (System.currentTimeMillis() - currentTimeMillis) + "\n" + MemoryUtils.dumpProcessMemoryStats());
        if (cachedPssMegaBytes > 1800) {
            ImageDecoder.dump((PrintWriter) null);
        }
    }
}
