package com.samsung.android.gallery.support.trace;

import android.os.Trace;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TraceCompatProfileableImpl implements TraceCompat {
    public void beginSection(String str) {
        Trace.beginSection(str);
    }

    public void endSection() {
        Trace.endSection();
    }
}
