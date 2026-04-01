package com.samsung.android.gallery.plugins.portrait;

import A.a;
import com.samsung.android.gallery.module.fileio.redact.OnProgress;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements OnProgress {
    public final void onCompleted(int i2, int i7, int i8) {
        a.w(a.h(i2, i7, "save redacted files: ", ", success: ", ", fail: "), i8, "LiveEffectDelegate");
    }
}
