package com.samsung.android.gallery.module.cloud;

import O3.l;
import Sd.B;
import Sd.e;
import Sd.x;
import Sd.y;
import Sd.z;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.samsung.android.gallery.module.cloud.sdk.SamsungCloud2Sdk;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SamsungCloud2Status {
    private static final String TAG = "SamsungCloud2Status";
    private final Object LOCK = new Object();
    private final Object LOCK_OBSERVER = new Object();
    private Bundle mAlbumSyncData;
    private Consumer<Bundle> mAlbumSyncDataChangeListener;
    private volatile boolean mIsGracePeriod = GalleryPreference.getInstanceCache().loadBoolean("cloud_service_grace_period", false);
    private volatile boolean mIsPermissionRequired = GalleryPreference.getInstanceCache().loadBoolean("cloud_service_permission_required", false);
    private volatile boolean mIsShowTips = GalleryPreference.getInstanceCache().loadBoolean("cloud_service_show_tips", false);
    private final ArrayList<Closeable> mObservers = new ArrayList<>();
    private volatile long mOneDriveEndDate = GalleryPreference.getInstanceCache().loadLong("cloud_service_one_drive_end_date", 0);
    private volatile int mServiceAvailable = GalleryPreference.getInstanceCache().loadInt("cloud_service_available", SamsungCloud2Sdk.SERVICE_LEGACY_STATUS_REQUIRED);
    private Consumer<Integer> mServiceChangeListener;
    private Consumer<Boolean> mSyncSettingListener;
    private Consumer<Integer> mSyncStatusListener;

    /* access modifiers changed from: private */
    public void onAlbumSyncDataChanged(e eVar) {
        Consumer<Bundle> consumer;
        Log.d(TAG, "onAlbumSyncDataChanged", Logger.toString(eVar.e));
        Bundle bundle = eVar.e;
        if (updateAlbumSyncDataIfChanged(bundle) && (consumer = this.mAlbumSyncDataChangeListener) != null) {
            consumer.accept(bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onAvailabilityChanged(y yVar) {
        Consumer<Integer> consumer;
        int ordinal = yVar.e.ordinal();
        boolean z = yVar.f;
        boolean z3 = yVar.g;
        long j2 = yVar.f3723h;
        boolean contains = yVar.f3724i.contains("PERMISSION_REQUIRED");
        Log.d(TAG, "onAvailabilityChanged", yVar.e, Boolean.valueOf(z), Boolean.valueOf(z3), Long.valueOf(j2), Boolean.valueOf(contains));
        boolean updateServiceIfChanged = updateServiceIfChanged(ordinal);
        boolean updateExtrasIfChanged = updateExtrasIfChanged(z, z3, j2, contains);
        if ((updateServiceIfChanged || updateExtrasIfChanged) && (consumer = this.mServiceChangeListener) != null) {
            if (!updateServiceIfChanged) {
                ordinal = -1;
            }
            consumer.accept(Integer.valueOf(ordinal));
        }
    }

    /* access modifiers changed from: private */
    public void onSyncSettingChanged(z zVar) {
        Consumer<Boolean> consumer;
        Log.d(TAG, "onSyncSettingChanged", zVar.name());
        boolean equals = z.On.equals(zVar);
        if (updateSyncOnIfChanged(equals) && (consumer = this.mSyncSettingListener) != null) {
            consumer.accept(Boolean.valueOf(equals));
        }
    }

    /* access modifiers changed from: private */
    public void onSyncStatusChanged(B b) {
        Consumer<Integer> consumer;
        Log.d(TAG, "onSyncStatusChanged", b.name());
        if (updateSyncStatusIfChanged(b.ordinal()) && (consumer = this.mSyncStatusListener) != null) {
            consumer.accept(Integer.valueOf(b.ordinal()));
        }
    }

    private boolean updateAlbumSyncDataIfChanged(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        if (this.mAlbumSyncData == null || !bundle.keySet().equals(this.mAlbumSyncData.keySet())) {
            this.mAlbumSyncData = bundle;
            return true;
        }
        for (String next : bundle.keySet()) {
            if (bundle.get(next) != this.mAlbumSyncData.get(next)) {
                this.mAlbumSyncData = bundle;
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x001d A[Catch:{ all -> 0x0018 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean updateExtrasIfChanged(boolean r4, boolean r5, long r6, boolean r8) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.LOCK
            monitor-enter(r0)
            boolean r1 = r3.mIsShowTips     // Catch:{ all -> 0x0018 }
            if (r1 != r4) goto L_0x001a
            boolean r1 = r3.mIsGracePeriod     // Catch:{ all -> 0x0018 }
            if (r1 != r5) goto L_0x001a
            long r1 = r3.mOneDriveEndDate     // Catch:{ all -> 0x0018 }
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 != 0) goto L_0x001a
            boolean r1 = r3.mIsPermissionRequired     // Catch:{ all -> 0x0018 }
            if (r1 == r8) goto L_0x0016
            goto L_0x001a
        L_0x0016:
            r1 = 0
            goto L_0x001b
        L_0x0018:
            r3 = move-exception
            goto L_0x0053
        L_0x001a:
            r1 = 1
        L_0x001b:
            if (r1 == 0) goto L_0x0051
            r3.mIsShowTips = r4     // Catch:{ all -> 0x0018 }
            r3.mIsGracePeriod = r5     // Catch:{ all -> 0x0018 }
            r3.mOneDriveEndDate = r6     // Catch:{ all -> 0x0018 }
            r3.mIsPermissionRequired = r8     // Catch:{ all -> 0x0018 }
            com.samsung.android.gallery.support.utils.GalleryPreference r4 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstanceCache()     // Catch:{ all -> 0x0018 }
            java.lang.String r5 = "cloud_service_show_tips"
            boolean r6 = r3.mIsShowTips     // Catch:{ all -> 0x0018 }
            r4.saveState((java.lang.String) r5, (boolean) r6)     // Catch:{ all -> 0x0018 }
            com.samsung.android.gallery.support.utils.GalleryPreference r4 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstanceCache()     // Catch:{ all -> 0x0018 }
            java.lang.String r5 = "cloud_service_grace_period"
            boolean r6 = r3.mIsGracePeriod     // Catch:{ all -> 0x0018 }
            r4.saveState((java.lang.String) r5, (boolean) r6)     // Catch:{ all -> 0x0018 }
            com.samsung.android.gallery.support.utils.GalleryPreference r4 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstanceCache()     // Catch:{ all -> 0x0018 }
            java.lang.String r5 = "cloud_service_one_drive_end_date"
            long r6 = r3.mOneDriveEndDate     // Catch:{ all -> 0x0018 }
            r4.saveState((java.lang.String) r5, (long) r6)     // Catch:{ all -> 0x0018 }
            com.samsung.android.gallery.support.utils.GalleryPreference r4 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstanceCache()     // Catch:{ all -> 0x0018 }
            java.lang.String r5 = "cloud_service_permission_required"
            boolean r3 = r3.mIsPermissionRequired     // Catch:{ all -> 0x0018 }
            r4.saveState((java.lang.String) r5, (boolean) r3)     // Catch:{ all -> 0x0018 }
        L_0x0051:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return r1
        L_0x0053:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.cloud.SamsungCloud2Status.updateExtrasIfChanged(boolean, boolean, long, boolean):boolean");
    }

    private boolean updateServiceIfChanged(int i2) {
        boolean z;
        synchronized (this.LOCK) {
            try {
                if (this.mServiceAvailable != i2) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    this.mServiceAvailable = i2;
                    GalleryPreference.getInstanceCache().saveState("cloud_service_available", this.mServiceAvailable);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    private boolean updateSyncOnIfChanged(boolean z) {
        GalleryPreference instance = GalleryPreference.getInstance();
        PreferenceName preferenceName = PreferenceName.IS_SCLOUD_SYNC_ON;
        if (instance.loadBoolean(preferenceName, false) == z) {
            return false;
        }
        GalleryPreference.getInstance().saveState(preferenceName, z);
        return true;
    }

    private boolean updateSyncStatusIfChanged(int i2) {
        if (GalleryPreference.getInstanceCache().loadInt("cloud_sync_status", -1) == i2) {
            return false;
        }
        GalleryPreference.getInstanceCache().saveState("cloud_sync_status", i2);
        return true;
    }

    public void checkServiceAvailable(Context context) {
        Consumer<Integer> consumer;
        int i2;
        y requestCheckIfServiceIsAvailable = SamsungCloud2Sdk.requestCheckIfServiceIsAvailable(context);
        if (requestCheckIfServiceIsAvailable != null) {
            x xVar = requestCheckIfServiceIsAvailable.e;
            if (requestCheckIfServiceIsAvailable.a()) {
                boolean updateServiceIfChanged = updateServiceIfChanged(xVar.ordinal());
                boolean updateExtrasIfChanged = updateExtrasIfChanged(requestCheckIfServiceIsAvailable.f, requestCheckIfServiceIsAvailable.g, requestCheckIfServiceIsAvailable.f3723h, requestCheckIfServiceIsAvailable.f3724i.contains("PERMISSION_REQUIRED"));
                if ((updateServiceIfChanged || updateExtrasIfChanged) && (consumer = this.mServiceChangeListener) != null) {
                    if (updateServiceIfChanged) {
                        i2 = xVar.ordinal();
                    } else {
                        i2 = -1;
                    }
                    consumer.accept(Integer.valueOf(i2));
                }
            }
        }
    }

    public long getOneDriveEndDate() {
        long j2;
        synchronized (this.LOCK) {
            j2 = this.mOneDriveEndDate;
        }
        return j2;
    }

    public int getService() {
        int i2;
        synchronized (this.LOCK) {
            i2 = this.mServiceAvailable;
        }
        return i2;
    }

    public int getSyncStatusInPref() {
        return GalleryPreference.getInstanceCache().loadInt("cloud_sync_status", -1);
    }

    public boolean isAccountRequired() {
        if (getService() == SamsungCloud2Sdk.SERVICE_ACCOUNT_REQUIRED) {
            return true;
        }
        return false;
    }

    public boolean isAccountRequiredForNewGallery() {
        if (getService() == SamsungCloud2Sdk.SERVICE_ACCOUNT_REQUIRED_FOR_NEW_GALLERY) {
            return true;
        }
        return false;
    }

    public boolean isGracePeriod() {
        boolean z;
        synchronized (this.LOCK) {
            z = this.mIsGracePeriod;
        }
        return z;
    }

    public boolean isLegacyServiceStatusRequired() {
        if (getService() == SamsungCloud2Sdk.SERVICE_LEGACY_STATUS_REQUIRED) {
            return true;
        }
        return false;
    }

    public boolean isMigrationAvailable() {
        if (getService() == SamsungCloud2Sdk.SERVICE_MIGRATION_AVAILABLE) {
            return true;
        }
        return false;
    }

    public boolean isNewGalleryAvailable() {
        if (getService() == SamsungCloud2Sdk.SERVICE_NEW_GALLERY_AVAILABLE) {
            return true;
        }
        return false;
    }

    public boolean isOneDriveAvailable() {
        if (getService() == SamsungCloud2Sdk.SERVICE_ONE_DRIVE_AVAILABLE) {
            return true;
        }
        return false;
    }

    public boolean isOneDriveLinkRequired() {
        if (getService() == SamsungCloud2Sdk.SERVICE_ONE_DRIVE_LINK_REQUIRED) {
            return true;
        }
        return false;
    }

    public boolean isPermissionRequired() {
        boolean z;
        synchronized (this.LOCK) {
            z = this.mIsPermissionRequired;
        }
        return z;
    }

    public boolean isServiceNotAvailable() {
        if (getService() == SamsungCloud2Sdk.SERVICE_NOT_AVAILABLE) {
            return true;
        }
        return false;
    }

    public boolean isShowTips() {
        boolean z;
        synchronized (this.LOCK) {
            z = this.mIsShowTips;
        }
        return z;
    }

    public boolean isSubscriptionRequired() {
        if (getService() == SamsungCloud2Sdk.SERVICE_SUBSCRIPTION_REQUIRED) {
            return true;
        }
        return false;
    }

    public boolean isSubscriptionRequiredLinkedBefore() {
        if (getService() == SamsungCloud2Sdk.SERVICE_SUBSCRIPTION_REQUIRED_LINKED_BEFORE) {
            return true;
        }
        return false;
    }

    public boolean loadConfig(Context context) {
        Consumer<Bundle> consumer;
        Consumer<Integer> consumer2;
        boolean z;
        Consumer<Boolean> consumer3;
        int requestGetSyncSetting = SamsungCloud2Sdk.requestGetSyncSetting(context);
        if (requestGetSyncSetting >= 0) {
            if (requestGetSyncSetting == SamsungCloud2Sdk.SYNC_SETTING_ON) {
                z = true;
            } else {
                z = false;
            }
            if (updateSyncOnIfChanged(z) && (consumer3 = this.mSyncSettingListener) != null) {
                consumer3.accept(Boolean.valueOf(z));
            }
        }
        int requestGetSyncStatus = SamsungCloud2Sdk.requestGetSyncStatus(context);
        if (requestGetSyncStatus >= 0 && updateSyncStatusIfChanged(requestGetSyncStatus) && (consumer2 = this.mSyncStatusListener) != null) {
            consumer2.accept(Integer.valueOf(requestGetSyncStatus));
        }
        Bundle requestGetAlbumStatus = SamsungCloud2Sdk.requestGetAlbumStatus(context);
        if (updateAlbumSyncDataIfChanged(requestGetAlbumStatus) && (consumer = this.mAlbumSyncDataChangeListener) != null) {
            consumer.accept(requestGetAlbumStatus);
        }
        return true;
    }

    public void register(Context context) {
        synchronized (this.LOCK_OBSERVER) {
            if (this.mObservers.isEmpty()) {
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    Handler bgThreadHandler = ThreadUtil.getBgThreadHandler();
                    this.mObservers.add(new D0.e(context, 17).P(new a(0, this)));
                    this.mObservers.add(new D0.e(context, 17).R(bgThreadHandler, new a(1, this)));
                    this.mObservers.add(new D0.e(context, 17).Q(bgThreadHandler, new a(2, this)));
                    this.mObservers.add(new D0.e(context, 17).O(new a(3, this)));
                    String str = TAG;
                    Log.d(str, "register" + Logger.vt(Integer.valueOf(this.mObservers.size()), Long.valueOf(currentTimeMillis)));
                } catch (Exception e) {
                    String str2 = TAG;
                    Log.e(str2, "register failed, e=" + e.getMessage());
                }
            }
        }
    }

    public SamsungCloud2Status setAlbumSyncDataChangeListener(Consumer<Bundle> consumer) {
        this.mAlbumSyncDataChangeListener = consumer;
        return this;
    }

    public SamsungCloud2Status setServiceChangeListener(Consumer<Integer> consumer) {
        this.mServiceChangeListener = consumer;
        return this;
    }

    public SamsungCloud2Status setSyncSettingListener(Consumer<Boolean> consumer) {
        this.mSyncSettingListener = consumer;
        return this;
    }

    public SamsungCloud2Status setSyncStatusListener(Consumer<Integer> consumer) {
        this.mSyncStatusListener = consumer;
        return this;
    }

    public void unregister() {
        synchronized (this.LOCK_OBSERVER) {
            try {
                int size = this.mObservers.size();
                if (size > 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    this.mObservers.forEach(new l(19));
                    this.mObservers.clear();
                    String str = TAG;
                    Log.d(str, "unregister" + Logger.vt(Integer.valueOf(size), Long.valueOf(currentTimeMillis)));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
