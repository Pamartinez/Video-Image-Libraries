package com.samsung.android.gallery.support.library.v16.system;

import V8.a;
import android.content.Context;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import com.samsung.android.gallery.support.library.v0.system.DexInfo;
import com.samsung.android.gallery.support.library.v12.system.DexInfo150;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DexInfo170 extends DexInfo150 {
    private final int FLAG_EXTERNAL_DESKTOP_WINDOWING = 131072;

    public DexInfo compute(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        this.key = context.getClass().getSimpleName() + Log.TAG_SEPARATOR + Integer.toHexString(context.hashCode());
        int displayId = context.getDisplay().getDisplayId();
        if (!(displayId == 0 || displayId == -1)) {
            DisplayManagerCompat displayManagerCompat = SeApiCompat.getDisplayManagerCompat(context);
            if (displayManagerCompat == null) {
                android.util.Log.w("DexInfo", "getDisplayManagerCompat is null");
                return this;
            }
            int intValue = ((Integer) Optional.ofNullable(displayManagerCompat.getDisplay(displayId)).map(new a(1)).orElse(0)).intValue();
            if ((131072 & intValue) != 0) {
                this.connected = true;
                this.enabled = true;
            }
            StringBuilder h5 = A.a.h(displayId, intValue, "compute ", ArcCommonLog.TAG_COMMA, ArcCommonLog.TAG_COMMA);
            h5.append(this);
            h5.append(" +");
            h5.append(System.currentTimeMillis() - currentTimeMillis);
            android.util.Log.d("DexInfo", h5.toString());
        }
        return this;
    }
}
