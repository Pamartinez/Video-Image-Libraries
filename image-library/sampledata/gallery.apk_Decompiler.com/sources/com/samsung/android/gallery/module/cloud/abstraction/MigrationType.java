package com.samsung.android.gallery.module.cloud.abstraction;

import A.a;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MigrationType {
    ;
    
    private static final String ACTION_MIGRATION_RETRY = "com.samsung.android.scloud.app.broadcast.ACTION_SYNC_GALLERY_BY_FORCE";
    public static final int INVALID_STATE = -1;
    public static final int MIGRATION_STATE_COMPLETE = 2;
    public static final int MIGRATION_STATE_MIGRATING = 1;
    public static final int MIGRATION_STATE_NO_NEED_MIGRATION = 6;
    public static final int MIGRATION_STATE_PENDING = 3;
    public static final int MIGRATION_STATE_PENDING_MOBILE = 5;
    public static final int MIGRATION_STATE_PENDING_WIFI = 4;
    public static final int MIGRATION_STATE_READY = 0;
    private static final String MIGRATION_SYNC_ACTION = "sync_action";
    private static final String TAG = "MigrationType";

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.cloud.abstraction.MigrationType$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends MigrationType {
        public /* synthetic */ AnonymousClass1() {
            this("MIGRATING", 0);
        }

        public String getDescription(Context context) {
            return context.getString(R$string.mig_tip_optimizing_media_description, new Object[]{StringResources.getCloudBrand()});
        }

        public String getDoneBtnString(Context context) {
            return null;
        }

        public String getTitle(Context context) {
            return context.getString(R$string.mig_tip_optimizing_media_title);
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }

        public void actionDone(Context context) {
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.cloud.abstraction.MigrationType$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends MigrationType {
        public /* synthetic */ AnonymousClass2() {
            this("PENDING", 1);
        }

        public void actionDone(Context context) {
            MigrationType.sendMigrationSyncEnableBroadCast(context, this);
        }

        public String getDescription(Context context) {
            return context.getString(R$string.mig_tip_optimizing_media_pause_description);
        }

        public String getDoneBtnString(Context context) {
            return context.getString(R$string.mig_tip_optimizing_media_pause_btn_retry);
        }

        public String getTitle(Context context) {
            return context.getString(R$string.mig_tip_optimizing_media_pause_title);
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.cloud.abstraction.MigrationType$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends MigrationType {
        public /* synthetic */ AnonymousClass3() {
            this("PENDING_NETWORK", 2);
        }

        public void actionDone(Context context) {
            MigrationType.sendMigrationSyncEnableBroadCast(context, this);
        }

        public String getDescription(Context context) {
            String cloudBrand = StringResources.getCloudBrand();
            return context.getString(R$string.mig_tip_waiting_to_optimize_media_description, new Object[]{cloudBrand, cloudBrand});
        }

        public String getDoneBtnString(Context context) {
            return context.getString(R$string.mig_tip_waiting_to_optimize_media_btn_sync_now);
        }

        public String getTitle(Context context) {
            return context.getString(R$string.mig_tip_waiting_to_optimize_media_title);
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }
    }

    public static MigrationType get(int i2) {
        if (i2 == 1) {
            return MIGRATING;
        }
        if (i2 == 3) {
            return PENDING;
        }
        if (i2 == 4 || i2 == 5) {
            return PENDING_NETWORK;
        }
        a.B(i2, "invalid migrationType : ", TAG);
        return null;
    }

    public static boolean hasNoAction(int i2) {
        if (i2 == -1 || i2 == 0 || i2 == 2 || i2 == 6) {
            return true;
        }
        return false;
    }

    public static void sendMigrationSyncEnableBroadCast(Context context, MigrationType migrationType) {
        Intent intent = new Intent(ACTION_MIGRATION_RETRY);
        intent.putExtra(MIGRATION_SYNC_ACTION, true);
        context.getApplicationContext().sendBroadcast(intent);
        Log.i(TAG, "sendMigrationSyncEnableBroadCast " + migrationType.name());
    }

    public abstract void actionDone(Context context);

    public abstract String getDescription(Context context);

    public abstract String getDoneBtnString(Context context);

    public abstract String getTitle(Context context);
}
