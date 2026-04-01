package com.samsung.android.gallery.support.library.v0.system;

import N2.j;
import android.content.Context;
import com.samsung.android.desktopmode.SemDesktopModeManager;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DexInfo {
    protected Boolean dexOnPc;
    protected boolean enabled;
    protected String key;

    private boolean isDexMode(Context context) {
        try {
            Object invoke = Reflector.invoke((Class<?>) SemDesktopModeManager.class, (Object) (SemDesktopModeManager) context.getSystemService("desktopmode"), "isDesktopMode");
            if (!(invoke instanceof Boolean) || !((Boolean) invoke).booleanValue()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            j.D(e, new StringBuilder("isDexMode failed e="), "DexInfo");
            return false;
        }
    }

    public DexInfo compute(Context context) {
        this.key = context.getClass().getSimpleName() + Log.TAG_SEPARATOR + Integer.toHexString(context.hashCode());
        this.enabled = isDexMode(context);
        return this;
    }

    public boolean isDexOnPc(Context context) {
        boolean z = false;
        if (!this.enabled) {
            return false;
        }
        if (this.dexOnPc == null) {
            if (SeApiCompat.getSettingsSystemInt(context, "dexonpc_connection_state", 0) == 3) {
                z = true;
            }
            this.dexOnPc = Boolean.valueOf(z);
        }
        return this.dexOnPc.booleanValue();
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isStandAlone() {
        return false;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("DexInfo{");
        sb2.append(this.key);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return j.h(sb2, this.enabled, "}");
    }
}
