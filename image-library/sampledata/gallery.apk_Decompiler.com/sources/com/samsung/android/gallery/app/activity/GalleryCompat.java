package com.samsung.android.gallery.app.activity;

import A.a;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.module.debugger.ExceptionHandler;
import com.samsung.android.gallery.module.debugger.PerformanceTracker;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberManager;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.exception.GalleryUncaughtExceptionHandler;
import com.samsung.android.gallery.support.exception.InternalAnr;
import com.samsung.android.gallery.support.exception.InternalAnrBg;
import com.samsung.android.gallery.support.exception.NativeCrashLogger;
import com.samsung.android.gallery.support.providers.GmpCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MemoryUtils;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SafeMode;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.support.utils.ThreadWatchDog;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.support.utils.WatchDog;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class GalleryCompat implements WatchDog.WatchdogCallback {
    private static final boolean DEBUG_VIEW = SafeMode.ENABLED;
    /* access modifiers changed from: private */
    public static final boolean ENABLE_GMP_ALL = PocFeatures.isEnabled(PocFeatures.GmpAll);
    /* access modifiers changed from: private */
    public static final boolean ENABLE_GMP_LOC_ONLY = PocFeatures.isEnabled(PocFeatures.GmpLocOnly);
    private final Blackboard mBlackboard;
    private final AtomicInteger mColorMode = new AtomicInteger(1);
    private final long mConstructTime;
    private boolean mDebugFromDevTool;
    private DebugView mDebugView;
    private ExceptionHandler mExceptionHandler;
    private WatchDog mWatchdog;
    private boolean mWideColorGamut;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DebugView {
        private final Blackboard mBlackboard;
        WeakReference<TextView> mInfoViewRef;
        private final String mVersionInfo;

        public DebugView(Blackboard blackboard) {
            String str;
            this.mBlackboard = blackboard;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(PackageMonitorCompat.getVersionName());
            sb2.append("[SDK36]");
            if (GalleryCompat.ENABLE_GMP_ALL) {
                str = "[GMP(A)]";
            } else if (GalleryCompat.ENABLE_GMP_LOC_ONLY) {
                str = "[GMP(L)]";
            } else if (Features.isEnabled(Features.USE_SEC_MP)) {
                str = "[SEC-MP]";
            } else {
                str = "";
            }
            sb2.append(str);
            this.mVersionInfo = sb2.toString();
        }

        public void addFloatingView(View view, int i2, int i7, float f) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 24, -3);
            layoutParams.x = i2;
            layoutParams.y = i7;
            layoutParams.alpha = f;
            layoutParams.gravity = 8388659;
            try {
                ((WindowManager) view.getContext().getSystemService("window")).addView(view, layoutParams);
            } catch (Exception e) {
                a.s(e, new StringBuilder("addFloatingView failed e="), "DebugView");
            }
        }

        public TextView getDebugView() {
            WeakReference<TextView> weakReference = this.mInfoViewRef;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        public void hide() {
            removeFloatingView(getDebugView());
        }

        public void removeFloatingView(View view) {
            if (view != null) {
                try {
                    ((WindowManager) view.getContext().getSystemService("window")).removeView(view);
                } catch (Exception e) {
                    a.s(e, new StringBuilder("removeFloatingView failed e="), "DebugView");
                }
            }
        }

        public abstract void show(Context context);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SafeModeNotice extends DebugView {
        public SafeModeNotice(Blackboard blackboard) {
            super(blackboard);
        }

        public void show(Context context) {
            WeakReference<TextView> weakReference = this.mInfoViewRef;
            if (weakReference == null || weakReference.get() == null) {
                TextView textView = new TextView(context);
                textView.setTextSize(1, 14.0f);
                textView.setTextColor(-65536);
                textView.setTypeface(textView.getTypeface(), 3);
                textView.setText(context.getString(R.string.safe_mode));
                this.mInfoViewRef = new WeakReference<>(textView);
            }
            int dpToPixel = ResourceCompat.dpToPixel(context, 5.0f);
            addFloatingView(this.mInfoViewRef.get(), dpToPixel, dpToPixel, 1.0f);
        }
    }

    public GalleryCompat(Blackboard blackboard, long j2) {
        this.mBlackboard = blackboard;
        this.mConstructTime = j2;
        MemoryUtils.stopAssertOnLargeUsedHeap();
        if (SafeMode.ENABLED) {
            this.mDebugView = new SafeModeNotice(blackboard);
        }
        ThreadPool.getInstance().setExpireCallback(new f(this));
    }

    private void pauseJobService(Activity activity) {
        if (ENABLE_GMP_LOC_ONLY || ENABLE_GMP_ALL) {
            Log.d("GalleryCompat", "pauseJobService");
            GmpCompat.setOnUi(false);
        }
    }

    private void resumeJobService(Activity activity) {
        if (ENABLE_GMP_LOC_ONLY || ENABLE_GMP_ALL) {
            Log.d("GalleryCompat", "resumeJobService");
            GmpCompat.setOnUi(true);
            GmpCompat.scheduleDataSync(activity.getApplicationContext(), false);
        }
    }

    private void startWatchDog() {
        try {
            if (this.mWatchdog == null) {
                boolean isEnabled = PocFeatures.isEnabled(PocFeatures.DumpSlowMessage);
                Log.d("GalleryCompat", "createWatchDog {" + isEnabled + "}");
                WatchDog watchDog = new WatchDog();
                this.mWatchdog = watchDog;
                watchDog.createLooperWatchDogLogging(Looper.getMainLooper(), isEnabled, this);
            }
            this.mWatchdog.setActivate(true);
        } catch (Exception unused) {
        }
    }

    private void stopWatchDog() {
        WatchDog watchDog = this.mWatchdog;
        if (watchDog != null) {
            watchDog.setActivate(false);
        }
    }

    public void createJobService(Activity activity) {
        if (ENABLE_GMP_LOC_ONLY || ENABLE_GMP_ALL) {
            Log.d("GalleryCompat", "createJobService");
            GmpCompat.setOnUi(true);
        }
    }

    public boolean isWideColorGamut() {
        return this.mWideColorGamut;
    }

    public void onAnr() {
        boolean z;
        try {
            IGalleryActivityView iGalleryActivityView = (IGalleryActivityView) BlackboardUtils.readActivity(this.mBlackboard);
            StringBuilder sb2 = new StringBuilder("onAnr ");
            if (iGalleryActivityView != null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            Log.d("GalleryCompat", sb2.toString());
            if (iGalleryActivityView != null && iGalleryActivityView.isActivityResumed()) {
                new InternalAnr("INTERNAL ANR").post();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("onAnr failed e="), "GalleryCompat");
        }
    }

    public void onAnrBg(StackTraceElement[] stackTraceElementArr) {
        boolean z;
        try {
            IGalleryActivityView iGalleryActivityView = (IGalleryActivityView) BlackboardUtils.readActivity(this.mBlackboard);
            StringBuilder sb2 = new StringBuilder("onAnrBg ");
            if (iGalleryActivityView != null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            Log.d("GalleryCompat", sb2.toString());
            if (iGalleryActivityView != null && iGalleryActivityView.isActivityResumed()) {
                new InternalAnrBg("INTERNAL ANR : ThreadPool").post(stackTraceElementArr);
            }
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("onAnrBg failed e="), "GalleryCompat");
        }
    }

    public void onCreate(Activity activity, SubscriberManager subscriberManager, long j2) {
        subscriberManager.add(new PerformanceTracker(this.mBlackboard, this.mConstructTime, j2));
        if (this.mExceptionHandler == null) {
            this.mExceptionHandler = new ExceptionHandler(activity);
        }
        GalleryUncaughtExceptionHandler.getInstance().addExceptionHandler(this.mExceptionHandler);
    }

    public void onDestroy(Activity activity) {
        if (this.mDebugFromDevTool) {
            activity.setResult(-568);
        }
        if (this.mExceptionHandler != null) {
            GalleryUncaughtExceptionHandler.getInstance().removeExceptionHandler(this.mExceptionHandler);
            this.mExceptionHandler = null;
        }
        WatchDog watchDog = this.mWatchdog;
        if (watchDog != null) {
            watchDog.quit();
            this.mWatchdog = null;
        }
        MemoryUtils.assertOnLargeUsedHeap(100);
    }

    public void onPause(Activity activity) {
        DebugView debugView;
        pauseJobService(activity);
        stopWatchDog();
        if (DEBUG_VIEW && (debugView = this.mDebugView) != null) {
            debugView.hide();
        }
        NativeCrashLogger.onPause();
        ThumbnailLoader.getInstance().onActivityPause();
        ThreadWatchDog.setActive(false);
    }

    public void onResume(Activity activity) {
        DebugView debugView;
        if (TimeUtil.updateToday()) {
            Blackboard.getApplicationInstance().post("global://event/dateTimeChanged", Long.valueOf(System.currentTimeMillis()));
        }
        resumeJobService(activity);
        startWatchDog();
        if (DEBUG_VIEW && (debugView = this.mDebugView) != null) {
            debugView.show(activity);
        }
        NativeCrashLogger.onResume();
        ThumbnailLoader.getInstance().onActivityResume();
        ThreadWatchDog.setActive(true);
    }

    public void onSlowChoreographer() {
        try {
            GalleryActivity galleryActivity = (GalleryActivity) BlackboardUtils.readActivity(this.mBlackboard);
            if (galleryActivity != null && galleryActivity.isActivityResumed()) {
                Log.w("GalleryCompat", "onSlowChoreographer");
            }
        } catch (Error | Exception unused) {
        }
    }

    public void resetColorMode(Activity activity, Configuration configuration) {
        try {
            if (SuperHdrConfig.SUPPORT && SuperHdrConfig.isEnabled()) {
                Log.d("GalleryCompat", "resetColorMode SKIP(HDR)", Logger.toColorMode(this.mColorMode.get()), Boolean.valueOf(this.mWideColorGamut));
            } else if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                this.mColorMode.set(configuration.isScreenWideColorGamut() ? 1 : 0);
                activity.getWindow().setColorMode(this.mColorMode.get());
                Log.d("GalleryCompat", "resetColorMode", Logger.toColorMode(this.mColorMode.get()), Boolean.valueOf(this.mWideColorGamut));
            } else if (this.mWideColorGamut || !configuration.isScreenWideColorGamut()) {
                this.mColorMode.set(configuration.isScreenWideColorGamut() ? 1 : 0);
                activity.getWindow().setColorMode(this.mColorMode.get());
                Log.d("GalleryCompat", "resetColorMode", Logger.toColorMode(this.mColorMode.get()), Boolean.valueOf(this.mWideColorGamut));
            } else {
                Log.d("GalleryCompat", "resetColorMode not supported > restart");
                Utils.finishAndStartGalleryActivity(activity);
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("resetColorMode failed e="), "GalleryCompat");
        }
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [boolean, int] */
    public void setDisplayMode(Activity activity, Configuration configuration) {
        try {
            ? isScreenWideColorGamut = configuration.isScreenWideColorGamut();
            this.mWideColorGamut = isScreenWideColorGamut;
            this.mColorMode.set(isScreenWideColorGamut);
            if (isScreenWideColorGamut > 0) {
                activity.getWindow().setColorMode(isScreenWideColorGamut);
            }
            this.mBlackboard.publish("data://user/WindowColorMode", this.mColorMode);
            Log.d("GalleryCompat", "setDisplayMode#ColorMode", Logger.toColorMode(isScreenWideColorGamut), Boolean.valueOf(this.mWideColorGamut));
        } catch (Exception e) {
            a.s(e, new StringBuilder("setDisplayMode#ColorMode failed e="), "GalleryCompat");
        }
    }
}
