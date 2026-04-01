package com.samsung.android.gallery.support.library.v12.system;

import N2.j;
import android.content.Context;
import android.provider.Settings;
import com.samsung.android.desktopmode.SemDesktopModeState;
import com.samsung.android.gallery.support.library.v0.system.DexInfo;
import com.samsung.android.gallery.support.library.v0.system.DexInfo95;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DexInfo150 extends DexInfo95 {
    boolean newDexMode;

    private boolean isNewDexMode(Context context) {
        try {
            if (Settings.System.getInt(context.getContentResolver(), "new_dex") == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            j.D(e, new StringBuilder("isNewDexMode failed e="), "DexInfo");
            return false;
        }
    }

    public DexInfo compute(Context context) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        this.key = context.getClass().getSimpleName() + Log.TAG_SEPARATOR + Integer.toHexString(context.hashCode());
        this.state = getDexState(context);
        this.newDexMode = isNewDexMode(context);
        SemDesktopModeState semDesktopModeState = this.state;
        boolean z3 = false;
        if (semDesktopModeState == null || semDesktopModeState.enabled != 4) {
            z = false;
        } else {
            z = true;
        }
        this.connected = z;
        if ((z && !isDexDualMode(context)) || this.newDexMode) {
            z3 = true;
        }
        this.enabled = z3;
        android.util.Log.d("DexInfo", "compute " + this + " +" + (System.currentTimeMillis() - currentTimeMillis));
        return this;
    }

    public boolean isStandAlone() {
        if (this.newDexMode || super.isStandAlone()) {
            return true;
        }
        return false;
    }
}
