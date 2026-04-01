package com.samsung.android.gallery.support.library.v0;

import android.content.Context;
import com.samsung.android.gallery.support.library.v0.system.DexInfo;
import com.samsung.android.gallery.support.library.v0.system.DexInfo90;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem90ApiCompatImpl extends Sem85ApiCompatImpl {
    public DexInfo computeDexInfo(Context context) {
        return new DexInfo90().compute(context);
    }
}
