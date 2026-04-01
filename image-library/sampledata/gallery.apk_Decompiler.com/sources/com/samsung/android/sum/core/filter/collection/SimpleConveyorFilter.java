package com.samsung.android.sum.core.filter.collection;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.SequentialDescriptor;
import com.samsung.android.sum.core.filter.MediaFilter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleConveyorFilter extends SequentialFilter {
    public SimpleConveyorFilter(SequentialDescriptor sequentialDescriptor) {
        super(sequentialDescriptor);
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        MutableMediaBuffer mutableMediaBuffer2 = null;
        for (MediaFilter run : this.filters) {
            mutableMediaBuffer2 = run.run(mediaBuffer);
            mediaBuffer = mutableMediaBuffer2;
        }
        if (mutableMediaBuffer2 != null) {
            mutableMediaBuffer.put((MediaBuffer) mutableMediaBuffer2);
        }
        return mutableMediaBuffer;
    }
}
