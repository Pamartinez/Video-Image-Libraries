package com.samsung.android.gallery.support.library.v11;

import N2.j;
import android.content.Context;
import android.provider.Settings;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem125ApiCompatImpl extends Sem121ApiCompatImpl {
    public int getPinnedEdgeWidth(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "pinned_edge_width", 0);
    }

    public boolean isLeftPinnedEdge(Context context) {
        if (!isPinEdgeEnabled(context) || Settings.System.getInt(context.getContentResolver(), "active_edge_area", 1) != 0) {
            return false;
        }
        return true;
    }

    public boolean isPinEdgeEnabled(Context context) {
        try {
            if (!isMainScreen(context.getResources().getConfiguration()) || Settings.System.getInt(context.getContentResolver(), "panel_mode", 0) != 1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            j.C(e, new StringBuilder("isPinEdgeEnabled failed e="), this.TAG);
            return false;
        }
    }

    public void performHapticFeedback(Context context, int i2) {
        if (i2 == 14) {
            i2 = 108;
        }
        super.performHapticFeedback(context, i2);
    }
}
