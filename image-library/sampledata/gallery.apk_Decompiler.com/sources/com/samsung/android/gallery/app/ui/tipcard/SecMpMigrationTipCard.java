package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.sec.android.gallery3d.R;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SecMpMigrationTipCard extends AbsTipCard {
    private static final String FILE_PATH = ("/data/sec/." + SeApiCompat.getMyUserId() + "_migration_");
    private static final int[] MIGRATION_STEP = {0, 1, 2, 3};

    private boolean isMigrationCompleted() {
        return PreferenceCache.SecMpMigrationCompleted.getBoolean();
    }

    private void setMigrationComplete() {
        PreferenceCache.SecMpMigrationCompleted.setBoolean(true);
    }

    public boolean checkTipCard(Context context) {
        if (isMigrationCompleted()) {
            return false;
        }
        try {
            for (int i2 : MIGRATION_STEP) {
                if (new File(FILE_PATH + i2).exists()) {
                    Log.d(this.TAG, "migration in progress, step=" + i2);
                    return true;
                }
            }
        } catch (Exception e) {
            Log.d(this.TAG, e.getMessage());
        }
        Log.d(this.TAG, "sec mp migration is completed");
        setMigrationComplete();
        return false;
    }

    public String getDescription(Context context) {
        return context.getString(R.string.mig_tip_optimizing_media_description_for_secmp);
    }

    public String getTag() {
        return getClass().getSimpleName();
    }

    public String getTipCardCreationLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_SEC_MP_MIGRATION.toString();
    }

    public String getTitle(Context context) {
        return context.getString(R.string.mig_tip_optimizing_media_title);
    }
}
