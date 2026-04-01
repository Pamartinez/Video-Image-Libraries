package com.samsung.android.gallery.module.smartswitch;

import N2.j;
import android.net.Uri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SmartSwitchState {
    INIT,
    NOT_RESTORE,
    RESTORING,
    RESTORE_COMPLETED;
    
    private static final Uri SMART_SWITCH_AUTHORITY_URI = null;
    private static final String TAG = "SmartSwitchState";

    static {
        SMART_SWITCH_AUTHORITY_URI = Uri.parse("content://com.sec.android.easyMover.BnRProvider/Restoring/com.sec.android.gallery3d");
    }

    public static boolean isRestoringNow() {
        boolean z;
        try {
            z = "TRUE".equalsIgnoreCase(AppResources.getAppContext().getContentResolver().getType(SMART_SWITCH_AUTHORITY_URI));
        } catch (Exception e) {
            j.s(e, new StringBuilder("isRestoringNow failed e="), TAG);
            z = false;
        }
        Log.d(TAG, "isRestoringNow", Boolean.valueOf(z));
        return z;
    }

    public static int load() {
        return PreferenceCache.SmartSwitchRestoreState.getInt();
    }

    public static void save(SmartSwitchState smartSwitchState) {
        PreferenceCache.SmartSwitchRestoreState.setInt(smartSwitchState.ordinal());
    }
}
