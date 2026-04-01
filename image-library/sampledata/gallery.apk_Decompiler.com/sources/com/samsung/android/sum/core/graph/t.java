package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.functional.BufferSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements BufferSupplier {
    public final /* synthetic */ MediaFilter d;

    public /* synthetic */ t(MediaFilter mediaFilter) {
        this.d = mediaFilter;
    }

    public final MediaBuffer getBuffer() {
        return ((BufferSupplier) this.d).getBuffer();
    }
}
