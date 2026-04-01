package com.samsung.android.gallery.support.library.v0.system;

import android.content.Context;
import com.samsung.android.desktopmode.SemDesktopModeManager;
import com.samsung.android.desktopmode.SemDesktopModeState;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DexInfo90 extends DexInfo {
    protected SemDesktopModeState state;

    public DexInfo compute(Context context) {
        boolean z;
        this.key = context.getClass().getSimpleName() + Log.TAG_SEPARATOR + Integer.toHexString(context.hashCode());
        SemDesktopModeState dexState = getDexState(context);
        this.state = dexState;
        if (dexState == null || dexState.enabled != 4) {
            z = false;
        } else {
            z = true;
        }
        this.enabled = z;
        return this;
    }

    public final SemDesktopModeState getDexState(Context context) {
        try {
            SemDesktopModeManager semDesktopModeManager = (SemDesktopModeManager) context.getSystemService("desktopmode");
            if (semDesktopModeManager != null) {
                return semDesktopModeManager.getDesktopModeState();
            }
            return null;
        } catch (Exception e) {
            android.util.Log.e("DexInfo", "getDexState failed", e);
            return null;
        }
    }
}
