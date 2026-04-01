package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.types.Status;

@FunctionalInterface
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ExecuteDelegator {
    Status execute(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer, BiBufferProcessor biBufferProcessor);
}
