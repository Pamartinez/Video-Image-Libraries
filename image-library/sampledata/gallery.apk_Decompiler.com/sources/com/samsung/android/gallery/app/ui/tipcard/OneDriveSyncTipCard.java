package com.samsung.android.gallery.app.ui.tipcard;

import N2.j;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.StorageUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDriveSyncTipCard extends AbsOneDriveTipCard {
    private StorageState mStorageState;
    private double mStorageUsage = -1.0d;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum StorageState {
        ;

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.app.ui.tipcard.OneDriveSyncTipCard$StorageState$1  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass1 extends StorageState {
            public /* synthetic */ AnonymousClass1() {
                this("USAGE_50", 0);
            }

            public boolean checkPreference() {
                return PreferenceCache.OneDriveStorageTipCard50.getBoolean();
            }

            public void disableTipCard() {
                PreferenceCache.OneDriveStorageTipCard50.setBoolean(false);
                Log.d("OneDriveSyncTipCard", "disableTipCard, storage usage : 50");
            }

            private AnonymousClass1(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.app.ui.tipcard.OneDriveSyncTipCard$StorageState$2  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass2 extends StorageState {
            public /* synthetic */ AnonymousClass2() {
                this("USAGE_70", 1);
            }

            public boolean checkPreference() {
                return PreferenceCache.OneDriveStorageTipCard70.getBoolean();
            }

            public void disableTipCard() {
                PreferenceCache.OneDriveStorageTipCard70.setBoolean(false);
                PreferenceCache.OneDriveStorageTipCard50.setBoolean(false);
                Log.d("OneDriveSyncTipCard", "disableTipCard, storage usage : 70");
            }

            private AnonymousClass2(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.app.ui.tipcard.OneDriveSyncTipCard$StorageState$3  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass3 extends StorageState {
            public /* synthetic */ AnonymousClass3() {
                this("USAGE_90", 2);
            }

            public boolean checkPreference() {
                return PreferenceCache.OneDriveStorageTipCard90.getBoolean();
            }

            public void disableTipCard() {
                PreferenceCache.OneDriveStorageTipCard90.setBoolean(false);
                PreferenceCache.OneDriveStorageTipCard70.setBoolean(false);
                PreferenceCache.OneDriveStorageTipCard50.setBoolean(false);
                Log.d("OneDriveSyncTipCard", "disableTipCard, storage usage : 90");
            }

            private AnonymousClass3(String str, int i2) {
                super(str, i2, 0);
            }
        }

        public abstract boolean checkPreference();

        public abstract void disableTipCard();
    }

    private boolean checkStorageUsage() {
        StorageState storageState = StorageState.USAGE_90;
        if (!storageState.checkPreference() || !isStorageUsage90()) {
            StorageState storageState2 = StorageState.USAGE_70;
            if (!storageState2.checkPreference() || !isStorageUsage70()) {
                StorageState storageState3 = StorageState.USAGE_50;
                if (!storageState3.checkPreference() || !isStorageUsage50()) {
                    return false;
                }
                this.mStorageState = storageState3;
                Log.d(this.TAG, "checkStorageUsage : 50");
                return true;
            }
            this.mStorageState = storageState2;
            Log.d(this.TAG, "checkStorageUsage : 70");
            return true;
        }
        this.mStorageState = storageState;
        Log.d(this.TAG, "checkStorageUsage : 90");
        return true;
    }

    private double getStorageUsage() {
        if (this.mStorageUsage == -1.0d) {
            this.mStorageUsage = StorageUtil.getStorageUsage();
        }
        return this.mStorageUsage;
    }

    private boolean isStorageUsage50() {
        if (getStorageUsage() >= 0.5d) {
            return true;
        }
        return false;
    }

    private boolean isStorageUsage70() {
        if (getStorageUsage() >= 0.699999988079071d) {
            return true;
        }
        return false;
    }

    private boolean isStorageUsage90() {
        if (getStorageUsage() >= 0.8999999761581421d) {
            return true;
        }
        return false;
    }

    public boolean checkTipCard(Context context) {
        if (!super.checkTipCard(context) || !CloudStateCompat.isMigrationSupported()) {
            return false;
        }
        if ((CloudStateCompat.isNone() || CloudStateCompat.isUnknown()) && checkStorageUsage()) {
            return true;
        }
        return false;
    }

    public void disableTipCard() {
        this.mStorageState.disableTipCard();
    }

    public String getCancelBtnString(Context context) {
        return context.getString(R.string.not_now);
    }

    public String getDescription(Context context) {
        return context.getString(R.string.one_drive_tip_sync_with_body);
    }

    public String getDoneBtnString(Context context) {
        return context.getString(R.string.get_started);
    }

    public String getTag() {
        return "OneDriveSyncTipCard";
    }

    public String getTipCardCreationLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_ONE_DRIVE_DEVICE_STORAGE_LIMIT.toString();
    }

    public String getTipCardDoneClickLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_ONE_DRIVE_START_DEVICE_STORAGE_LIMIT.toString();
    }

    public String getTitle(Context context) {
        return context.getResources().getString(R.string.one_drive_tip_sync_with_title);
    }

    public void startActivity(Context context) {
        try {
            ((Activity) context).startActivityForResult(new Intent("com.samsung.android.scloud.app.activity.LAUNCH_MIGRATION_AGREEMENT"), 1281);
        } catch (ActivityNotFoundException e) {
            j.p(e, new StringBuilder("AgreementActivity not found="), this.TAG);
        }
    }
}
