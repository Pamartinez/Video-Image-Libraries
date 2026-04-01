package com.samsung.android.gallery.app.ui.tipcard;

import A.a;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import com.samsung.android.gallery.module.cloud.abstraction.MigrationType;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SCloudMigrationTipCard extends AbsTipCard {
    private MigrationType mMigrationType;

    private boolean checkMigrationPreference() {
        return PreferenceCache.SCloudMigrationComplete.getBoolean();
    }

    private int loadMigrationState() {
        return PreferenceCache.SCloudMigrationState.getInt();
    }

    private void saveMigrationState(int i2) {
        PreferenceCache.SCloudMigrationState.setInt(i2);
    }

    private void updateMigrationCompletePreference(int i2) {
        if (i2 == 2 || i2 == 6) {
            PreferenceCache.SCloudMigrationComplete.setBoolean(true);
            a.k(i2, "updateMigrationPreference : ", this.TAG);
        }
    }

    public boolean checkTipCard(Context context) {
        if (checkMigrationPreference()) {
            Log.d(this.TAG, "migration preference complete state");
            return false;
        }
        int migrationStateFromPolicy = getMigrationStateFromPolicy(context);
        a.k(migrationStateFromPolicy, "migration state : ", this.TAG);
        int loadMigrationState = loadMigrationState();
        if (migrationStateFromPolicy == 0 && loadMigrationState > migrationStateFromPolicy) {
            String str = this.TAG;
            Log.d(str, "invalid migration state changed : " + migrationStateFromPolicy + " , " + loadMigrationState);
            migrationStateFromPolicy = 2;
        }
        if (MigrationType.hasNoAction(migrationStateFromPolicy)) {
            updateMigrationCompletePreference(migrationStateFromPolicy);
            return false;
        }
        if (loadMigrationState != migrationStateFromPolicy) {
            saveMigrationState(migrationStateFromPolicy);
        }
        this.mMigrationType = MigrationType.get(migrationStateFromPolicy);
        return true;
    }

    public String getDescription(Context context) {
        return this.mMigrationType.getDescription(context);
    }

    public String getDoneBtnString(Context context) {
        return this.mMigrationType.getDoneBtnString(context);
    }

    public int getMigrationStateFromPolicy(Context context) {
        Cursor query;
        Throwable th;
        try {
            query = context.getContentResolver().query(MediaUri.getInstance().getScloudPolicy(), new String[]{"value"}, "key = 'migration_state'", (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    int i2 = query.getInt(0);
                    String str = this.TAG;
                    Log.d(str, "Migration policy : " + i2);
                    query.close();
                    return i2;
                }
            }
            Log.d(this.TAG, "Migration policy skip. null cursor");
            if (query == null) {
                return 2;
            }
            query.close();
            return 2;
        } catch (Exception e) {
            Exception exc = e;
            a.s(exc, new StringBuilder("Migration policy failed. e="), this.TAG);
            return 2;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public String getTag() {
        return this.mMigrationType.name();
    }

    public String getTipCardCreationLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_MEDIA_DATA_MIGRATION.toString();
    }

    public String getTitle(Context context) {
        return this.mMigrationType.getTitle(context);
    }

    public boolean isTipCardAvailable() {
        if (this.mMigrationType != null) {
            return true;
        }
        return false;
    }

    public void onDoneBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        this.mMigrationType.actionDone(context);
    }
}
