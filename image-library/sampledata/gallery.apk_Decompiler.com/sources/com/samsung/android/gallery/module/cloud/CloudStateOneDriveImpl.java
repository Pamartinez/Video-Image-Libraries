package com.samsung.android.gallery.module.cloud;

import A.a;
import N2.j;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.cloud.abstraction.CloudLinkState;
import com.samsung.android.gallery.module.cloud.abstraction.CloudState;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CloudStateOneDriveImpl implements CloudState {
    static final Uri ONE_DRIVE_URI = Uri.parse("content://com.samsung.android.scloud.app.ui.datamigrator.linkcontext");
    private final Object LOCK = new Object();
    protected String mLinkState = GalleryPreference.getInstance().loadString(PreferenceName.ONE_DRIVE_LINK_STATE, CloudLinkState.Invalid.name());
    private volatile boolean mLoadingCompleted;
    private boolean mMigrationSupported = GalleryPreference.getInstance().loadBoolean(PreferenceName.ONE_DRIVE_MIGRATION_SUPPORTED, false);
    private boolean mPermissionRequired;
    private boolean mSupportAllowedOperations;
    private boolean mSyncOn;
    private long mTimeStamp;
    private Pair<Long, Bundle> mTrialBundle;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BundleHolder {
        static final Bundle empty;

        static {
            Bundle bundle = new Bundle();
            empty = bundle;
            bundle.putString("LinkState", CloudLinkState.Invalid.name());
        }
    }

    public CloudStateOneDriveImpl() {
        Log.d("CloudStateOneDriveImpl", "OneDrive#Pref{" + this.mMigrationSupported + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mLinkState + "}");
    }

    private Bundle queryLinkState() {
        Bundle callSafe = AndroidCompat.callSafe(AppResources.getAppContext(), ONE_DRIVE_URI, "GET_MIGRATION_STATE", (String) null, (Bundle) null);
        Log.d("CloudStateOneDriveImpl", "queryLinkState " + Logger.toString(callSafe));
        if (callSafe != null) {
            return callSafe;
        }
        return BundleHolder.empty;
    }

    private Bundle queryQuota() {
        ThreadUtil.assertBgThread("queryQuota should run on background thread");
        Bundle bundle = new Bundle();
        bundle.putString("function", "GetPartnersQuota");
        Bundle callSafe = AndroidCompat.callSafe(AppResources.getAppContext(), ONE_DRIVE_URI, "CLOUD_SETTING", (String) null, bundle);
        Log.d("CloudStateOneDriveImpl", "queryQuota " + Logger.toString(callSafe));
        if (callSafe != null) {
            return callSafe;
        }
        return BundleHolder.empty;
    }

    private boolean querySyncStatus() {
        Bundle bundle = new Bundle();
        bundle.putString("function", "IsGallerySyncOn");
        Bundle callSafe = AndroidCompat.callSafe(AppResources.getAppContext(), ONE_DRIVE_URI, "CLOUD_SETTING", (String) null, bundle);
        Log.d("CloudStateOneDriveImpl", "querySyncStatus " + Logger.toString(callSafe));
        if (callSafe == null || callSafe.getInt("value1", 0) != 1) {
            return false;
        }
        return true;
    }

    private Bundle queryTrialStatus() {
        ThreadUtil.assertBgThread("queryTrialStatus should run on background thread");
        Bundle callSafe = AndroidCompat.callSafe(AppResources.getAppContext(), Uri.parse("content://com.microsoft.skydrive.content.Samsung100GBTrial"), (String) null, (String) null, (Bundle) null);
        Log.d("CloudStateOneDriveImpl", "queryTrialStatus " + Logger.toString(callSafe));
        if (callSafe != null) {
            return callSafe;
        }
        return BundleHolder.empty;
    }

    private void saveStatusInPref() {
        GalleryPreference.getInstance().saveState(PreferenceName.ONE_DRIVE_MIGRATION_SUPPORTED, this.mMigrationSupported);
        GalleryPreference.getInstance().saveState(PreferenceName.ONE_DRIVE_LINK_STATE, this.mLinkState);
        GalleryPreference.getInstance().saveState(PreferenceName.ONE_DRIVE_SYNC_ON, this.mSyncOn);
    }

    private boolean supportOneDrive() {
        if (Features.isEnabled(Features.IS_UPSM)) {
            return false;
        }
        if ((!Features.isEnabled(Features.IS_SEP_LITE) || SdkConfig.atLeast(SdkConfig.SEM.S)) && !Features.isEnabled(Features.IS_MUM_MODE)) {
            return true;
        }
        return false;
    }

    public boolean isEnabled() {
        boolean z;
        synchronized (this.LOCK) {
            try {
                if (!CloudLinkState.Migrating.equals(this.mLinkState)) {
                    if (!CloudLinkState.Migrated.equals(this.mLinkState)) {
                        z = false;
                    }
                }
                z = true;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public boolean isEnabledInPref() {
        String loadString = GalleryPreference.getInstance().loadString(PreferenceName.ONE_DRIVE_LINK_STATE, "");
        if (CloudLinkState.Migrating.equals(loadString) || CloudLinkState.Migrated.equals(loadString)) {
            return true;
        }
        return false;
    }

    public boolean isLoadingCompleted() {
        return this.mLoadingCompleted;
    }

    public boolean isMigrated() {
        boolean z;
        synchronized (this.LOCK) {
            try {
                if (!this.mMigrationSupported || !CloudLinkState.Migrated.equals(this.mLinkState) || !this.mSyncOn) {
                    z = false;
                } else {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public boolean isMigrationSupported() {
        boolean z;
        synchronized (this.LOCK) {
            z = this.mMigrationSupported;
        }
        return z;
    }

    public boolean isMigrationSupportedInPref() {
        return GalleryPreference.getInstance().loadBoolean(PreferenceName.ONE_DRIVE_MIGRATION_SUPPORTED, false);
    }

    public boolean isNewBadgeRequired() {
        boolean z;
        synchronized (this.LOCK) {
            try {
                if (isMigrationSupported()) {
                    if (isEnabled()) {
                        if (!this.mSyncOn) {
                        }
                    }
                    z = true;
                }
                z = false;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public boolean isNone() {
        boolean equals;
        synchronized (this.LOCK) {
            equals = CloudLinkState.None.equals(this.mLinkState);
        }
        return equals;
    }

    public boolean isPermissionRequired() {
        boolean z;
        synchronized (this.LOCK) {
            try {
                if (!isEnabled() || !this.mPermissionRequired) {
                    z = false;
                } else {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public boolean isSyncOnInPref() {
        return GalleryPreference.getInstance().loadBoolean(PreferenceName.ONE_DRIVE_SYNC_ON, false);
    }

    public boolean isUnknown() {
        boolean equals;
        synchronized (this.LOCK) {
            equals = CloudLinkState.Unknown.equals(this.mLinkState);
        }
        return equals;
    }

    public boolean isUnlinked() {
        boolean equals;
        synchronized (this.LOCK) {
            equals = CloudLinkState.Unlinked.equals(this.mLinkState);
        }
        return equals;
    }

    public boolean load(boolean z) {
        boolean z3;
        String str;
        boolean z7;
        boolean z9;
        boolean z10;
        boolean z11;
        HashSet hashSet;
        ThreadUtil.assertBgThread("CloudStateOneDriveImpl#load should run on background thread");
        boolean z12 = false;
        if (!supportOneDrive() || (!z && System.currentTimeMillis() - this.mTimeStamp < 180000)) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.LOCK) {
            z3 = this.mMigrationSupported;
            str = this.mLinkState;
            z7 = this.mSyncOn;
            z9 = this.mSupportAllowedOperations;
            z10 = this.mPermissionRequired;
        }
        Bundle queryLinkState = queryLinkState();
        boolean z13 = queryLinkState.getBoolean("IsMigrationSupported", false);
        String string = queryLinkState.getString("LinkState", CloudLinkState.Invalid.name());
        boolean querySyncStatus = querySyncStatus();
        boolean containsKey = queryLinkState.containsKey("AllowedOperations");
        if (!containsKey || (hashSet = (HashSet) queryLinkState.getSerializable("AllowedOperations")) == null || hashSet.isEmpty()) {
            z11 = false;
        } else {
            z11 = hashSet.contains("GalleryPermissionRequired");
        }
        if (!(z3 == z13 && str.equals(string) && z7 == querySyncStatus && z9 == containsKey && z10 == z11)) {
            z12 = true;
        }
        if (z12) {
            synchronized (this.LOCK) {
                this.mMigrationSupported = z13;
                this.mLinkState = string;
                this.mSyncOn = querySyncStatus;
                this.mSupportAllowedOperations = containsKey;
                this.mPermissionRequired = z11;
                saveStatusInPref();
            }
            Features.recycle(Features.SUPPORT_CLOUD);
            Blackboard.getApplicationInstance().publish("global://event/cloud/service/changed", (Object) null);
        }
        this.mLoadingCompleted = true;
        this.mTimeStamp = System.currentTimeMillis();
        Log.d("CloudStateOneDriveImpl", "load {" + z12 + "} " + this + " +" + (this.mTimeStamp - currentTimeMillis));
        return z12;
    }

    public float loadQuota() {
        ThreadUtil.assertBgThread("CloudStateOneDriveImpl#loadQuota should run on background thread");
        if (!supportOneDrive() || !isMigrationSupported()) {
            Log.d("CloudStateOneDriveImpl", "loadQuota {null}");
            return 0.0f;
        }
        Bundle queryQuota = queryQuota();
        long j2 = queryQuota.getLong("value1", 0);
        long j3 = queryQuota.getLong("value2", 0);
        StringBuilder j8 = j.j(j3, "loadQuota {usage=", ",total=");
        j8.append(j2);
        j8.append("}");
        Log.d("CloudStateOneDriveImpl", j8.toString());
        if (j2 != 0) {
            return ((float) j3) / ((float) j2);
        }
        return 0.0f;
    }

    public boolean loadTrialStatus() {
        ThreadUtil.assertBgThread("CloudStateOneDriveImpl#loadTrialStatus should run on background thread");
        if (supportOneDrive() && isEnabled()) {
            if (this.mTrialBundle == null || System.currentTimeMillis() - ((Long) this.mTrialBundle.first).longValue() > 60000) {
                this.mTrialBundle = new Pair<>(Long.valueOf(System.currentTimeMillis()), queryTrialStatus());
            }
            Bundle bundle = (Bundle) this.mTrialBundle.second;
            int i2 = bundle.getInt("TrialEligibilityResultCode", 0);
            Log.d("CloudStateOneDriveImpl", a.d(i2, bundle.getInt("GetTrialPeriod", 0), "loadTrialStatus {", ArcCommonLog.TAG_COMMA, "}"));
            if (i2 == 100) {
                return true;
            }
        }
        return false;
    }

    public void saveSyncOnInPref(boolean z) {
        GalleryPreference.getInstance().saveState(PreferenceName.ONE_DRIVE_SYNC_ON, z);
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("OneDrive{migrationSupported#");
        sb2.append(this.mMigrationSupported);
        sb2.append(",Link#");
        sb2.append(this.mLinkState);
        if (this.mSyncOn) {
            str = ",Sync#on";
        } else {
            str = ",Sync#off";
        }
        sb2.append(str);
        sb2.append(",AllowedOp#");
        sb2.append(this.mSupportAllowedOperations);
        sb2.append(",PermissionReq#");
        sb2.append(this.mPermissionRequired);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0212a.o(sb2, this.mTimeStamp, "}");
    }
}
