package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InstantFilter extends DecorateFilter {
    public InstantFilter(MediaFilter mediaFilter) {
        super(mediaFilter);
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        super.prepare();
        MutableMediaBuffer run = super.run(mediaBuffer, mutableMediaBuffer);
        super.release();
        return run;
    }

    public void prepare() {
    }

    public void release() {
    }
}
