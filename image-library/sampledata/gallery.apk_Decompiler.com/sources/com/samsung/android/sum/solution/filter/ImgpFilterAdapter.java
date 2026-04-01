package com.samsung.android.sum.solution.filter;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.filter.ImgpFilter;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.types.ImgpType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImgpFilterAdapter implements Operator {
    private final ImgpFilter imgpFilter;

    public ImgpFilterAdapter(ImgpFilter imgpFilter2) {
        this.imgpFilter = imgpFilter2;
    }

    public ImgpFilter getImgpFilter() {
        return this.imgpFilter;
    }

    public ImgpType getImgpType() {
        return (ImgpType) ((ImgpDescriptor) this.imgpFilter.getDescriptor()).getImgpType();
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        return this.imgpFilter.run(mediaBuffer, mutableMediaBuffer);
    }

    public MediaBuffer run(MediaBuffer mediaBuffer, MediaFormat mediaFormat) {
        return run(mediaBuffer, MediaBuffer.mutableOf(mediaFormat));
    }
}
