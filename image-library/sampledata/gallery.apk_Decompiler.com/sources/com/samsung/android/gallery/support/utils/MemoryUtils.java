package com.samsung.android.gallery.support.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MemoryUtils {
    static final Runnable execGc = new Object();
    private static String mLastHeapDumpPath;
    private static long mPeakPss;
    private static long mPss;
    private static long mPssTimer;
    private static int sIndex;

    public static String dumpHprof() {
        try {
            FileUtils.makeDirectoryIfAbsent(Environment.getExternalStorageDirectory() + "/Download");
            String str = Environment.getExternalStorageDirectory() + "/Download/gallery" + TimeUtil.getFileTimestamp() + ".hprof";
            if (!TextUtils.isEmpty(mLastHeapDumpPath)) {
                FileUtils.delete(mLastHeapDumpPath);
            }
            mLastHeapDumpPath = str;
            FileUtils.delete(str);
            Debug.dumpHprofData(str);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dumpProcessMemoryStats() {
        return C0212a.m("MemoryStats:summary(KB){", (String) getMemoryInfoMap().entrySet().stream().map(new C0670h(19)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), "}");
    }

    public static void forceFree(Bitmap... bitmapArr) {
        SimpleThreadPool.getInstance().execute(new C(5, bitmapArr));
        Runnable runnable = execGc;
        ThreadUtil.removeCallbackOnBgThread(runnable);
        ThreadUtil.postOnBgThreadDelayed(runnable, 10);
    }

    public static long getAvailableMemorySize(int i2) {
        File storageFile = getStorageFile(i2);
        if (!Environment.getExternalStorageState(storageFile).equals("mounted")) {
            return -1;
        }
        StatFs statFs = new StatFs(storageFile.getPath());
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    public static long getAvailableStorageSize(String str, String str2) {
        if (!"mounted".equals(str)) {
            return -1;
        }
        StatFs statFs = new StatFs(new File(str2).getPath());
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    public static long getCachedPssMegaBytes() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - mPssTimer > 1000) {
            long pss = Debug.getPss() >> 10;
            mPss = pss;
            mPssTimer = currentTimeMillis;
            if (pss > mPeakPss) {
                mPeakPss = pss;
            }
        }
        return mPss;
    }

    public static Map<String, String> getMemoryInfoMap() {
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        return memoryInfo.getMemoryStats();
    }

    private static long getRamInMB(Context context) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return 0;
            }
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            long j2 = memoryInfo.totalMem / 1048576;
            Log.d("MemoryUtils", "getRamInMB {" + j2 + "MB} +" + (System.currentTimeMillis() - currentTimeMillis));
            return j2;
        } catch (Exception e) {
            Log.d("MemoryUtils", "getRamInMB failed e=" + e.getMessage());
            return 0;
        }
    }

    private static File getStorageFile(int i2) {
        String str;
        if (i2 == 0) {
            str = FileUtils.EXTERNAL_STORAGE_DIR;
        } else {
            str = FileUtils.getSdcardDir();
        }
        return new File(str);
    }

    public static String getStorageState(String str) {
        String externalStorageState = Environment.getExternalStorageState(new File(str));
        if (!"unknown".equals(externalStorageState) || !str.startsWith("/storage/emulated/")) {
            return externalStorageState;
        }
        String str2 = StorageInfo.getDefault().root;
        Log.w("MemoryUtils", "getStorageState for " + str2);
        return Environment.getExternalStorageState(new File(str2));
    }

    public static long getTotalMemorySize(int i2) {
        File storageFile = getStorageFile(i2);
        if (!Environment.getExternalStorageState(storageFile).equals("mounted")) {
            return -1;
        }
        StatFs statFs = new StatFs(storageFile.getPath());
        return statFs.getBlockCountLong() * statFs.getBlockSizeLong();
    }

    public static boolean isLowRamDevice(Context context) {
        if (getRamInMB(context) <= 3072) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$dumpProcessMemoryStats$0(Map.Entry entry) {
        return ((String) entry.getKey()).replaceFirst("^summary.", "") + NumericEnum.SEP + ((String) entry.getValue());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$forceFree$3(Bitmap[] bitmapArr) {
        for (Bitmap bitmap : bitmapArr) {
            if (bitmap != null) {
                Log.w("BitmapUtils", "recycle bitmap=" + Logger.v(bitmap));
                bitmap.recycle();
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$2() {
        Log.v("BitmapUtils", "FORCE GC");
        Runtime.getRuntime().gc();
    }

    public static void stopAssertOnLargeUsedHeap() {
        sIndex++;
    }

    public static String getStorageState(int i2) {
        return Environment.getExternalStorageState(getStorageFile(i2));
    }

    public static void assertOnLargeUsedHeap(int i2) {
    }
}
