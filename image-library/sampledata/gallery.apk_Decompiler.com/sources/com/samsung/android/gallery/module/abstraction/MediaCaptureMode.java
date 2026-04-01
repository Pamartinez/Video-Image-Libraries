package com.samsung.android.gallery.module.abstraction;

import A.a;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MediaCaptureMode {
    NONE,
    HIGHLIGHT_FRC,
    INSTANT_SLOW_MO_CLIP;
    
    private static final String TAG = "MediaCaptureMode";

    public static MediaCaptureMode getMode(int i2) {
        try {
            return values()[i2];
        } catch (Exception e) {
            a.s(e, C0086a.o(i2, "wrong value came in. return default value none. value=", ", e="), TAG);
            return NONE;
        }
    }
}
