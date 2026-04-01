package com.samsung.android.gallery.support.library.v0.system;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import com.samsung.android.desktopmode.SemDesktopModeState;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DexInfo95 extends DexInfo90 {
    protected boolean connected;

    public DexInfo compute(Context context) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        this.key = context.getClass().getSimpleName() + Log.TAG_SEPARATOR + Integer.toHexString(context.hashCode());
        SemDesktopModeState dexState = getDexState(context);
        this.state = dexState;
        boolean z3 = false;
        if (dexState == null || dexState.enabled != 4) {
            z = false;
        } else {
            z = true;
        }
        this.connected = z;
        if (z && !isDexDualMode(context)) {
            z3 = true;
        }
        this.enabled = z3;
        android.util.Log.d("DexInfo", "compute " + this + " +" + (System.currentTimeMillis() - currentTimeMillis));
        return this;
    }

    public boolean isDexDualMode(Context context) {
        Display display;
        if (this.state.getDisplayType() == 102) {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                display = windowManager.getDefaultDisplay();
            } else {
                display = null;
            }
            if (display == null || display.getDisplayId() != 0) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean isStandAlone() {
        SemDesktopModeState semDesktopModeState = this.state;
        if (semDesktopModeState == null || semDesktopModeState.getDisplayType() != 101) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "DexInfo{" + this.key + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.enabled + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.connected + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.dexOnPc + "}";
    }
}
