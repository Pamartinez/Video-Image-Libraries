package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Operator {
    MutableMediaBuffer run(MediaBuffer mediaBuffer, MediaBuffer mediaBuffer2) {
        if (mediaBuffer2 instanceof MutableMediaBuffer) {
            return run(mediaBuffer, (MutableMediaBuffer) mediaBuffer2);
        }
        return run(mediaBuffer, mediaBuffer2.toMutable());
    }

    MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer);

    MutableMediaBuffer run(MediaBuffer mediaBuffer) {
        return run(mediaBuffer, MediaBuffer.mutableOf(new Object[0]));
    }
}
