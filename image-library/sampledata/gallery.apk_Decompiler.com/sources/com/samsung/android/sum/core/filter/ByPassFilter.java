package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ByPassFilter extends MediaFilterBase implements MediaFilter {
    private static final String TAG = Def.tagOf((Class<?>) ByPassFilter.class);
    private final MFDescriptor mfDescriptor;

    public ByPassFilter(MFDescriptor mFDescriptor) {
        this.mfDescriptor = mFDescriptor;
    }

    public MFDescriptor getDescriptor() {
        return this.mfDescriptor;
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        if (mutableMediaBuffer.isEmpty()) {
            mutableMediaBuffer.put((MediaBuffer) mediaBuffer.dup());
            return mutableMediaBuffer;
        }
        throw new UnsupportedOperationException("not implement yet");
    }

    public Stream<MediaFilter> stream() {
        return Stream.of(this);
    }
}
