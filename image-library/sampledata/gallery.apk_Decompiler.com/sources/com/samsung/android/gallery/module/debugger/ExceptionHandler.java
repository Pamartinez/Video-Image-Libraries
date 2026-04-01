package com.samsung.android.gallery.module.debugger;

import U9.b;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Process;
import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.module.R$bool;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.debugger.FileHistory;
import com.samsung.android.gallery.support.exception.DumpAllHandler;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MemoryUtils;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SearchLog;
import com.samsung.android.gallery.support.utils.ServiceManager;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.VerboseLogger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import com.samsung.android.sdk.moneta.common.Constants;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler, DumpAllHandler {
    private static volatile boolean sWorking = false;
    private WeakReference<Activity> mActivityRef;
    private PrintWriter mWriter;

    public ExceptionHandler(Activity activity) {
        this.mActivityRef = new WeakReference<>(activity);
    }

    /* access modifiers changed from: private */
    public void Log(String str) {
        PrintWriter printWriter = this.mWriter;
        if (printWriter == null) {
            Log.e("ExceptionHandler", str);
        } else {
            printWriter.println(str);
        }
    }

    private void checkPackageSimple(StringBuilder sb2, String str) {
        sb2.append(str);
        sb2.append(NumericEnum.SEP);
        boolean z = false;
        PackageInfo packageInfo = PackageMonitorCompat.getInstance().getPackageInfo(str, 0);
        if (packageInfo != null) {
            sb2.append("version=");
            sb2.append(packageInfo.versionName);
            sb2.append(", versionCode=");
            sb2.append(packageInfo.getLongVersionCode());
            sb2.append(", enabled=");
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo != null && applicationInfo.enabled) {
                z = true;
            }
            sb2.append(z);
            sb2.append(", installTime=");
            sb2.append(TimeUtil.getIsoLocalDateTime(packageInfo.firstInstallTime));
            if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                sb2.append(", lastUpdate=");
                sb2.append(TimeUtil.getIsoLocalDateTime(packageInfo.lastUpdateTime));
            }
        } else {
            sb2.append("not available");
        }
        sb2.append("\n");
    }

    private void copyCallstack(Throwable th, Activity activity) {
        String logFromStack = ThreadUtil.getLogFromStack(0, 20, th.getStackTrace());
        th.toString();
        logFromStack.replace("com.samsung.android.gallery", "gallery");
    }

    private void dumpOpenFiles() {
        String readProcessOutput = Logger.readProcessOutput("/system/bin/sh", "-c", C0086a.i(Process.myPid(), "lsof -p "));
        Log("====== opened file list =====");
        System.out.println(readProcessOutput);
        Log("=======       END         =======");
    }

    private String getDeviceInfo(Activity activity) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add("SDK=" + Build.VERSION.SDK_INT);
            arrayList.add("rotate=" + DeviceInfo.getDisplayRotation(activity));
            arrayList.add("user=" + SeApiCompat.getMyUserId());
        } catch (Exception unused) {
        }
        return "DeviceInfo{" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList) + "}";
    }

    private String getDisplayInfo(Activity activity) {
        try {
            return ResourceCompat.getDisplayMetrics(activity).toString();
        } catch (Exception unused) {
            return "DisplayMetrics{null}";
        }
    }

    private String getStorageInfo(Activity activity) {
        try {
            ArrayList arrayList = new ArrayList();
            if (!DeviceConfig.UNIT_TEST) {
                arrayList.add("internal=" + (MemoryUtils.getAvailableMemorySize(0) >> 20));
                if (FileUtils.isSdcardMounted(activity)) {
                    arrayList.add("sd-card=" + (MemoryUtils.getAvailableMemorySize(1) >> 20));
                }
            }
            return "StorageAvailable{" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList) + "}, " + SeApiCompat.getMountState(activity);
        } catch (Exception unused) {
            return "StorageAvailable{null}";
        }
    }

    private String getThemeInfo(Context context) {
        if (context == null) {
            try {
                context = AppResources.getAppContext();
            } catch (Exception unused) {
                return "Theme=[null]";
            }
        }
        if (context == null) {
            return "Theme=[null]";
        }
        return "Theme=[sec-theme=" + AppResources.getSecTheme() + ",night-theme=" + context.getResources().getBoolean(R$bool.isNightTheme) + ",light-status-bar=" + context.getResources().getBoolean(R$bool.is_light_status_bar) + ",light-navi-bar=" + context.getResources().getBoolean(R$bool.gallery_window_light_navigation_bar) + "]";
    }

    private void handleFatalException(Throwable th) {
        Log("== Gallery Dumps ==");
        Activity activity = this.mActivityRef.get();
        if (activity != null) {
            Log(getDeviceInfo(activity));
            Log(getDisplayInfo(activity));
            Log(getStorageInfo(activity));
        } else {
            Log("No activity available");
        }
        Log(getThemeInfo(activity));
        Log(Features.toDebugString());
        Log(GalleryPreference.dump());
        Log(PreferenceFeatures.toDebugString());
        Log(PocFeatures.toDebugString());
        Log(getPackageDebugInfo());
        Log("==");
        if (activity != null) {
            activity.dump((String) null, (FileDescriptor) null, this.mWriter, (String[]) null);
            if (th != null) {
                copyCallstack(th, activity);
            }
        }
        ThreadUtil.dumpThreads(new b(25, this), (String) null);
        if (th != null && th.getClass().getSimpleName().contains("CursorWindowAllocationException")) {
            dumpOpenFiles();
        }
        try {
            Log("== recently used ==");
            Log(FileHistory.get());
        } catch (Error | Exception unused) {
        }
        Log(SearchLog.dump());
        Log(ServiceManager.getInstance().toDebugString());
        Log(ThumbnailLoader.getInstance().dump());
        Log(VerboseLogger.dump());
        Log(BitmapUtils.toDebugString());
    }

    public void dumpAll(PrintWriter printWriter) {
        this.mWriter = printWriter;
        handleFatalException((Throwable) null);
        this.mWriter = null;
    }

    public String getPackageDebugInfo() {
        StringBuilder sb2 = new StringBuilder(1024);
        PackageInfo packageInfo = PackageMonitorCompat.getInstance().getPackageInfo("com.sec.android.gallery3d", 0);
        if (packageInfo != null) {
            sb2.append("========== GALLERY PACKAGE INFO ============\nVERSION NAME : ");
            sb2.append(packageInfo.versionName);
            sb2.append("\nVERSION CODE : ");
            sb2.append(packageInfo.getLongVersionCode());
            sb2.append("\nUI VERSION : 17.5\nBUILD TIME : ");
            sb2.append(TimeUtil.getIsoUtcDateTime(1767458898691L));
            sb2.append("\nFIRST INSTALL : ");
            sb2.append(TimeUtil.getIsoLocalDateTime(packageInfo.firstInstallTime));
            sb2.append("\nLAST UPDATE : ");
            sb2.append(TimeUtil.getIsoLocalDateTime(packageInfo.lastUpdateTime));
            sb2.append("\nLAST COMMIT : c963dd8\nSESL#8=[appcompat:2.0.47, coordinatorlayout:2.0.6, core:2.0.37, customview:2.0.0, drawerlayout:2.0.5, fragment:2.0.3, material:2.0.67, picker-basic:2.0.5, preference:2.0.3, recyclerview:2.0.32, slidingpanelayout:2.0.3, swiperefreshlayout:2.0.1, viewpager2:2.0.0]\n");
        } else {
            sb2.append("fail to find gallery\n");
        }
        checkPackageSimple(sb2, "com.samsung.android.gallery.plugins");
        checkPackageSimple(sb2, "com.android.providers.media");
        checkPackageSimple(sb2, "com.google.android.providers.media.module");
        checkPackageSimple(sb2, "com.samsung.android.providers.media");
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            checkPackageSimple(sb2, "com.samsung.android.providers.trash");
            checkPackageSimple(sb2, "com.samsung.android.photoremasterservice");
            checkPackageSimple(sb2, "com.samsung.mediasearch");
        }
        checkPackageSimple(sb2, "com.samsung.ipservice");
        checkPackageSimple(sb2, "com.samsung.faceservice");
        checkPackageSimple(sb2, "com.samsung.petservice");
        checkPackageSimple(sb2, Constants.SMART_SUGGESTIONS_PACKAGE_NAME);
        checkPackageSimple(sb2, BaseConstants.PACKAGE_INFO.PACKAGE_CORE_SERVICES);
        checkPackageSimple(sb2, "com.samsung.storyservice");
        checkPackageSimple(sb2, "com.samsung.cmh");
        checkPackageSimple(sb2, "com.samsung.android.scloud");
        checkPackageSimple(sb2, CommonUtils.MOBILE_SERVICE_PACKAGE_NAME);
        checkPackageSimple(sb2, ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME);
        checkPackageSimple(sb2, "com.sec.android.app.vepreload");
        checkPackageSimple(sb2, "com.samsung.app.newtrim");
        checkPackageSimple(sb2, "com.samsung.android.smartmirroring");
        checkPackageSimple(sb2, "com.sec.android.easyMover");
        checkPackageSimple(sb2, "com.samsung.android.widget.pictureframe");
        checkPackageSimple(sb2, "com.sec.android.app.camera");
        return sb2.toString();
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            if (!sWorking) {
                sWorking = true;
                handleFatalException(th);
                sWorking = false;
                return;
            }
            Log.e("ExceptionHandler", "skip handle fatal exception");
        } catch (Exception e) {
            Log("Exception inside of Exception Handler");
            e.printStackTrace();
        }
    }
}
