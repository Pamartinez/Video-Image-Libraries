package com.samsung.android.gallery.support.utils;

import A.a;
import N2.j;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PackageMonitorCompat {
    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> sBackupPackageList = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AppInfoHolder> mAppInfoList;
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> mPackageList;
    protected PackageManager mPackageManager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AppInfoHolder {
        final ApplicationInfo applicationInfo;

        public AppInfoHolder(ApplicationInfo applicationInfo2) {
            this.applicationInfo = applicationInfo2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Holder {
        static final PackageMonitorCompat sInstance = new PackageMonitorCompat(0);
    }

    public /* synthetic */ PackageMonitorCompat(int i2) {
        this();
    }

    public static void dumpLog(PrintWriter printWriter) {
        ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> concurrentHashMap = getInstance().mPackageList;
        if (concurrentHashMap.isEmpty()) {
            concurrentHashMap = sBackupPackageList;
        }
        printWriter.println(toDebugString(concurrentHashMap));
    }

    public static String getBuildSignature() {
        return "15.8.00.61#1580000061#1767426499377";
    }

    public static PackageMonitorCompat getInstance() {
        return Holder.sInstance;
    }

    private ConcurrentHashMap<String, Object> getPackageHolder(String str) {
        return this.mPackageList.computeIfAbsent(str, new C0670h(21));
    }

    private <T> T getPackageValue(String str, String str2) {
        return getPackageHolder(str).get(str2);
    }

    public static String getVersionCode() {
        return String.valueOf(getInstance().getPackageVersion("com.sec.android.gallery3d"));
    }

    public static String getVersionName() {
        PackageInfo packageInfo = getInstance().getPackageInfo("com.sec.android.gallery3d", 0);
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return "";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ AppInfoHolder lambda$getApplicationInfo$0(String str, int i2, String str2) {
        try {
            return new AppInfoHolder(getPackageManager().getApplicationInfo(str, i2));
        } catch (Exception e) {
            StringBuilder k = j.k("getApplicationInfo (", str2, ") failed. e=");
            k.append(e.getMessage());
            Log.e("PackageMonitorCompat", k.toString());
            return new AppInfoHolder((ApplicationInfo) null);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ConcurrentHashMap lambda$getPackageHolder$1(String str) {
        return new ConcurrentHashMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$toDebugString$2(StringBuilder sb2, String str, Object obj) {
        String str2 = "INSTALLED";
        if (str2.equals(str)) {
            if (!((Boolean) obj).booleanValue()) {
                str2 = "NOT_INSTALLED";
            }
            sb2.append(str2);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return;
        }
        String str3 = "ENABLED";
        if (str3.equals(str)) {
            if (!((Boolean) obj).booleanValue()) {
                str3 = "DISABLED";
            }
            sb2.append(str3);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return;
        }
        String str4 = "SETTING_ENABLED";
        if (str4.equals(str)) {
            if (!((Boolean) obj).booleanValue()) {
                str4 = "SETTING_DISABLED";
            }
            sb2.append(str4);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$toDebugString$3(StringBuilder sb2, String str, ConcurrentHashMap concurrentHashMap) {
        sb2.append(" [");
        sb2.append(str);
        sb2.append("] ");
        concurrentHashMap.forEach(new G(sb2, 1));
        sb2.append("\n");
    }

    public static void printDebug() {
        ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> concurrentHashMap = getInstance().mPackageList;
        if (concurrentHashMap.isEmpty()) {
            concurrentHashMap = sBackupPackageList;
        }
        Log.d("PackageMonitorCompat", toDebugString(concurrentHashMap));
    }

    private void putPackageInfo(String str, String str2, PackageInfo packageInfo) {
        ConcurrentHashMap<String, Object> packageHolder = getPackageHolder(str);
        packageHolder.put(str2, packageInfo);
        packageHolder.put("INSTALLED", Boolean.TRUE);
    }

    private void putPackageValue(String str, String str2, Object obj) {
        getPackageHolder(str).put(str2, obj);
    }

    private static String toDebugString(ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> concurrentHashMap) {
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return "PackageMonitor(0){null}";
        }
        try {
            StringBuilder sb2 = new StringBuilder(512);
            sb2.append("PackageMonitor(");
            sb2.append(concurrentHashMap.size());
            sb2.append(")[\n");
            concurrentHashMap.forEach(new G(sb2, 0));
            sb2.append("]");
            return sb2.toString();
        } catch (Exception unused) {
            return "PackageMonitor(0){null}";
        }
    }

    public void clear() {
        this.mPackageList.clear();
    }

    public ApplicationInfo getApplicationInfo(String str, int i2) {
        ConcurrentHashMap<String, AppInfoHolder> concurrentHashMap = this.mAppInfoList;
        return concurrentHashMap.computeIfAbsent(str + NumericEnum.SEP + i2, new H(this, str, i2, 0)).applicationInfo;
    }

    public CharSequence getApplicationLabel(PackageInfo packageInfo) {
        try {
            return this.mPackageManager.getApplicationLabel(packageInfo.applicationInfo);
        } catch (Exception unused) {
            return packageInfo.packageName;
        }
    }

    public Bundle getApplicationMetaData(String str) {
        ApplicationInfo applicationInfo = getApplicationInfo(str, 128);
        if (applicationInfo != null) {
            return applicationInfo.metaData;
        }
        return null;
    }

    public int getPackageEnabledSetting(String str) {
        try {
            return getPackageManager().getApplicationEnabledSetting(str);
        } catch (IllegalArgumentException e) {
            Log.e("PackageMonitorCompat", "getPackageEnabledSetting not exists. e=" + e.getMessage());
            return -1;
        } catch (Error | Exception e7) {
            a.z(e7, new StringBuilder("getPackageEnabledSetting failed. e="), "PackageMonitorCompat");
            return -2;
        }
    }

    public PackageInfo getPackageInfo(String str, int i2) {
        String str2 = str + NumericEnum.SEP + i2;
        PackageInfo packageInfo = (PackageInfo) getPackageValue(str, str2);
        if (packageInfo != null) {
            return packageInfo;
        }
        Boolean bool = (Boolean) getPackageValue(str, "INSTALLED");
        if (bool != null && !bool.booleanValue()) {
            return null;
        }
        try {
            PackageInfo packageInfo2 = getPackageManager().getPackageInfo(str, i2);
            if (packageInfo2 != null) {
                putPackageInfo(str, str2, packageInfo2);
                return packageInfo2;
            }
            if (i2 == 0) {
                putPackageValue(str, "INSTALLED", Boolean.FALSE);
            }
            return packageInfo2;
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            Log.w("PackageMonitorCompat", "getPackageInfo failed e=" + Logger.toSimpleString(e));
            return null;
        }
    }

    public PackageManager getPackageManager() {
        if (this.mPackageManager == null) {
            Context appContext = AppResources.getAppContext();
            if (appContext != null) {
                this.mPackageManager = appContext.getPackageManager();
            } else {
                Log.e("PackageMonitorCompat", "getPackageManager failed. null app context ");
            }
        }
        return this.mPackageManager;
    }

    public Signature[] getPackageSignatures(String str) {
        PackageInfo packageInfo;
        SigningInfo signingInfo;
        if (TextUtils.isEmpty(str) || (packageInfo = getInstance().getPackageInfo(str, 134217728)) == null || (signingInfo = packageInfo.signingInfo) == null) {
            return new Signature[0];
        }
        return signingInfo.getApkContentsSigners();
    }

    public long getPackageVersion(String str) {
        PackageInfo packageInfo = getPackageInfo(str, 0);
        if (packageInfo != null) {
            return packageInfo.getLongVersionCode();
        }
        return 0;
    }

    public boolean hasSystemFeature(String str) {
        PackageManager packageManager = getPackageManager();
        if (packageManager == null || !packageManager.hasSystemFeature(str)) {
            return false;
        }
        return true;
    }

    public boolean isInstalledPackageDisabled(String str) {
        if (!isPackageInstalled(str) || isPackageEnabled(str)) {
            return false;
        }
        return true;
    }

    public boolean isInstallerPackageOfGallery(String str) {
        boolean equals = str.equals(getPackageManager().getInstallerPackageName("com.sec.android.gallery3d"));
        Log.d("PackageMonitorCompat", "checkInstallerPackage = " + str + " > " + equals);
        return equals;
    }

    public boolean isPackageEnabled(String str) {
        boolean z;
        ApplicationInfo applicationInfo;
        Boolean bool = (Boolean) getPackageValue(str, "ENABLED");
        if (bool != null) {
            return bool.booleanValue();
        }
        PackageInfo packageInfo = getPackageInfo(str, 128);
        if (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null || !applicationInfo.enabled) {
            z = false;
        } else {
            z = true;
        }
        putPackageValue(str, "ENABLED", Boolean.valueOf(z));
        return z;
    }

    public boolean isPackageInstalled(String str) {
        Boolean bool = (Boolean) getPackageValue(str, "INSTALLED");
        if (bool != null) {
            return bool.booleanValue();
        }
        if (getPackageInfo(str, 0) != null) {
            return true;
        }
        return false;
    }

    public boolean isPackageSettingEnabled(String str) {
        boolean z;
        Boolean bool = (Boolean) getPackageValue(str, "SETTING_ENABLED");
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            int applicationEnabledSetting = getPackageManager().getApplicationEnabledSetting(str);
            if (2 == applicationEnabledSetting || 3 == applicationEnabledSetting) {
                z = false;
            } else {
                z = true;
            }
            putPackageValue(str, "SETTING_ENABLED", Boolean.valueOf(z));
            return z;
        } catch (Exception e) {
            Log.w("PackageMonitorCompat", "isPackageSettingEnabled failed e=" + Logger.toSimpleString((Throwable) e));
            if (e instanceof IllegalArgumentException) {
                putPackageValue(str, "INSTALLED", Boolean.FALSE);
            }
            putPackageValue(str, "SETTING_ENABLED", Boolean.FALSE);
            return false;
        }
    }

    public boolean isValidPackageVersion(String str, long j2) {
        PackageInfo packageInfo;
        try {
            PackageManager packageManager = getInstance().getPackageManager();
            if (packageManager == null || (packageInfo = packageManager.getPackageInfo(str, 0)) == null || packageInfo.getLongVersionCode() < j2) {
                return false;
            }
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("version check failed e="), "PackageMonitorCompat");
        }
        return false;
    }

    public void recycle() {
        sBackupPackageList.putAll(this.mPackageList);
        this.mPackageManager = null;
        clear();
    }

    public ResolveInfo resolveActivity(Intent intent, int i2) {
        PackageManager packageManager = getPackageManager();
        if (packageManager != null) {
            return packageManager.resolveActivity(intent, i2);
        }
        return null;
    }

    public String toString() {
        return toDebugString(this.mPackageList);
    }

    private PackageMonitorCompat() {
        this.mPackageList = new ConcurrentHashMap<>();
        this.mAppInfoList = new ConcurrentHashMap<>();
    }

    public void clear(String str) {
        this.mPackageList.remove(str);
    }

    public CharSequence getApplicationLabel(String str) {
        try {
            return getApplicationLabel(getPackageInfo(str, 0));
        } catch (Exception unused) {
            return str;
        }
    }
}
